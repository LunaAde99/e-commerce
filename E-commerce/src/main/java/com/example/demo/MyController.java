package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.Prodotto.ProdottoA;
import com.example.demo.Cesti.CestiA;

@Controller
public class MyController {

	private static final Logger logger = LoggerFactory.getLogger(MyController.class);

	private final List<Prodotto> listaProdotti = new ArrayList<>();
	private final List<ProdottoA> prodottiSelezionati = new ArrayList<>();
	private final List<Cesti> listaCestini = new ArrayList<>();
	private final List<CestiA> cestiniSelezionati = new ArrayList<>();

	@GetMapping("/")
	public String homePage() {
		return "home";
	}

	@GetMapping("/store")
	public String showStore(Model model) {
		populateStoreData();
		model.addAttribute("listaProdotti", listaProdotti);
		model.addAttribute("listaCestini", listaCestini);
		return "store";
	}

	@PostMapping("/buy")
	public String processPurchase(@RequestParam Map<String, String> prodottiInput,
			@RequestParam(name = "cestoSelezionato", required = false) String cestoSelezionato, Model model) {
		// Log per vedere i dati ricevuti
		logger.info("Prodotti ricevuti: {}", prodottiInput); // Log di debug per i prodotti
		logger.info("Cesto selezionato: {}", cestoSelezionato); // Log per il cesto

		// Pulisce le liste
		prodottiSelezionati.clear();
		cestiniSelezionati.clear();

		// Elaborazione dei prodotti
		int totaleProdotti = 0;
		for (Prodotto prodotto : listaProdotti) {
			String quantitaStr = prodottiInput.get(prodotto.getNome());
			int quantita = 0;
			try {
				if (quantitaStr != null && !quantitaStr.isEmpty()) {
					quantita = Integer.parseInt(quantitaStr);
				}
			} catch (NumberFormatException e) {
				logger.warn("Valore non valido per prodotto {}: {}", prodotto.getNome(), quantitaStr);
			}

			if (quantita > 0) {
				totaleProdotti += quantita * prodotto.getPrezzo();
				prodottiSelezionati.add(new ProdottoA(prodotto, quantita));
			}
		}

		// Log per vedere i prodotti selezionati
		logger.info("Prodotti selezionati: {}", prodottiSelezionati); // Log per i prodotti selezionati

		// Elaborazione del cesto selezionato
		int totaleCesti = 0;
		if (cestoSelezionato != null) {
			for (Cesti cesto : listaCestini) {
				if (cesto.getNome().equals(cestoSelezionato)) {
					totaleCesti += cesto.getPrezzo();
					cestiniSelezionati.add(new CestiA(cesto, 1));
					break;
				}
			}
		}

		// Aggiungi i dati al modello
		model.addAttribute("prodottiSelezionati", prodottiSelezionati);
		model.addAttribute("cestiniSelezionati", cestiniSelezionati);
		model.addAttribute("totaleProdotti", totaleProdotti);
		model.addAttribute("totaleCesti", totaleCesti);
		model.addAttribute("totaleFinale", totaleProdotti + totaleCesti);
		logger.info("Prodotti selezionati: {}", prodottiSelezionati);
		logger.info("Cestini selezionati: {}", cestiniSelezionati);

		return "recap"; // Ritorna alla vista recap
	}

	private void populateStoreData() {
		listaProdotti.clear();
		listaProdotti.add(new Prodotto("Porchetta", "Ariccia", 20, "Lazio",
				"https://m.media-amazon.com/images/I/61h4HpJmnyL.AC_SL1000.jpg"));
		listaProdotti.add(new Prodotto("Bonet di cioccolato", "Torino", 30, "Piemonte",
				"https://www.giallozafferano.it/images/2-220/Bonet_650x433_wm.jpg"));
		listaProdotti.add(new Prodotto("Panettone con canditi", "Bauli", 40, "Lombardia",
				"https://m.media-amazon.com/images/I/614OjEvhJ4L.AC_UL320.jpg"));
		listaProdotti.add(new Prodotto("Sigari", "H-Upman", 40, "Cuba", "https://shorturl.at/9WKWa"));

		listaCestini.clear();
		listaCestini.add(new Cesti("Cesto in vimini", 40));
		listaCestini.add(new Cesti("Cesto in legno", 35));
		listaCestini.add(new Cesti("Cesto in rattan", 45));
		listaCestini.add(new Cesti("Cesto in cartone", 15));
	}
}
