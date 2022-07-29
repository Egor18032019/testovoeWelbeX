package com.welbex.testovoe.controllers;

import com.welbex.testovoe.dto.TableDto;
import com.welbex.testovoe.factories.TableDtoFactories;
import com.welbex.testovoe.strore.entities.TableEntities;
import com.welbex.testovoe.strore.repository.TableRepository;
import com.welbex.testovoe.utils.Const;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
@RequestMapping(value = Const.API + Const.TABLE_URL)
public class ControllerTable {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    TableRepository tableRepository;

    @PostMapping()
    public ResponseEntity<Map<String, StringBuilder>> addStudent(
            @RequestParam(value = "date", required = true) String date,
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "quantity", required = true) String quantity,
            @RequestParam(value = "distance", required = true) String distance
    ) {
        Map<String, StringBuilder> answer = new HashMap<>();
        StringBuilder text = new StringBuilder();
        LocalDate dateForBD;
        int quantityForBD;
        long distanceForBD;
        try {
            dateForBD = LocalDate.parse(date, formatter);
            quantityForBD = Integer.parseInt(quantity);
            distanceForBD = Long.parseLong(distance);
        } catch (Exception e) {
            text.append("Exception");
            answer.put(e.getMessage(), text);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(answer);
        }

        final TableEntities pointForDB = TableEntities.builder()
                .date(dateForBD)
                .name(name)
                .quantity(quantityForBD)
                .distance(distanceForBD)
                .build();
        tableRepository.saveAndFlush(pointForDB);
        text.append(name);
        answer.put("Добавлена новая запись name ", text);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }

    @GetMapping()
    public ResponseEntity<List<TableDto>> getAllStudens() {
        List<TableEntities> foo = tableRepository.findAll();
        List<TableDto> allPointDB = foo
                .stream()
                .map(TableDtoFactories::makeTable)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.CREATED).body(allPointDB);
    }

    @DeleteMapping()
    public ResponseEntity<Map<String, StringBuilder>> deleteStudent(
            @RequestParam(value = "id", required = true) Long id
    ) {
        Map<String, StringBuilder> answer = new HashMap<>();
        StringBuilder text = new StringBuilder();

        boolean isHaveInBD = tableRepository.deleteTableEntitiesById(id) != 0;
        if (isHaveInBD) {
            text.append("Удалён пользователь № ").append(id);
            answer.put("answer", text);
            return ResponseEntity.status(HttpStatus.OK).body(answer);
        } else {
            text.append("Неправильный id = ").append(id);
            answer.put("answer", text);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(answer);
        }
    }
}
