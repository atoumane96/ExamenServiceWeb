package com.examenws.demo.controller;

import com.examenws.demo.repository.SearchRepository;
import com.examenws.demo.entity.DayInfo;
import com.examenws.demo.entity.Search;
import com.examenws.demo.entity.SearchItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@RestController
public class DayFinderController {

    @Autowired
    private SearchRepository searchRepository;

    @PostMapping(value = "/dayfinder",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DayInfo> getDayOfWeek(@RequestBody DayInfo dateRequest) {
            String dateStr = dateRequest.getDate();
            LocalDate date;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            date = LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body(null);
        }
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            String formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String day = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault());

            DayInfo dayInfo = new DayInfo(null,formattedDate, day);

            SearchItem searchItem = new SearchItem();
            searchItem.setRequest(dateStr);
            searchItem.setResponse(dayInfo);

            Search search = new Search();
            search.setSearchDate(LocalDateTime.now());
            //search.getSearchItems().add(searchItem);
            searchRepository.save(search);

        return ResponseEntity.ok(dayInfo);
    }

    @GetMapping("/historique")
    public ResponseEntity<List<Search>> getHistorique() {
        List<Search> historique = searchRepository.findAll();
        return ResponseEntity.ok(historique);
    }
}

