package com.seekster.WebCrawler.api.controllers;

import com.seekster.WebCrawler.api.dto.SettingDto;
import com.seekster.WebCrawler.api.exceptions.GenericException;
import com.seekster.WebCrawler.api.exceptions.ValidationException;
import com.seekster.WebCrawler.api.models.Setting;
import com.seekster.WebCrawler.api.response.Response;
import com.seekster.WebCrawler.api.services.SettingSvc;
import com.seekster.WebCrawler.constant.SettingType;
import com.seekster.WebCrawler.singleton.CrawlSettingSingleton;
import com.seekster.WebCrawler.singleton.RobotTxtSettingSingleton;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/settings")
@Tag(name = "Setting Controller", description = "Api related to fetch, create, update and delete settings")
public class SettingCntlr {

    @Autowired
    private SettingSvc settingSvc;
    CrawlSettingSingleton crawlSettingSingleton = CrawlSettingSingleton.getInstance();
    RobotTxtSettingSingleton robotTxtSettingSingleton = RobotTxtSettingSingleton.getInstance();

    @GetMapping(value = "/getList", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            operationId = "getList",
            summary = "To get List of Settings, Call this API",
            description = "getSettingList method is HTTP GET mapping so to get data from database."
    )
    public ResponseEntity<Response> getSettingList(@RequestParam @Valid SettingType settingType) {
        try {
            return ResponseEntity.ok(
                    Response.builder()
                            .responseTime(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("setting list fetched successfully!")
                            .data(Collections.singletonMap("settings", settingSvc.getSettingList(settingType)))
                            .build()
            );
        } catch (GenericException exception) {
            return ResponseEntity.ok(
                    Response.builder()
                            .responseTime(LocalDateTime.now())
                            .status(HttpStatus.NOT_FOUND)
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .message(exception.getMessage())
                            .build()
            );
        }
    }

    @PostMapping(value = "/add-setting", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            operationId = "add-setting",
            summary = "To add setting for crawling, Call this API",
            description = "createSetting method is HTTP POST mapping so to add data to database."
    )
    public ResponseEntity<Response> createSetting(@RequestBody @Valid SettingDto settingDto) {
        try {
            return ResponseEntity.ok(
                    Response.builder()
                            .responseTime(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("Setting Added successfully!")
                            .data(Collections.singletonMap("settings", settingSvc.createSetting(settingDto)))
                            .build()
            );
        } catch (IllegalArgumentException | ValidationException exception) {
            return ResponseEntity.ok(
                    Response.builder()
                            .responseTime(LocalDateTime.now())
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message(exception.getMessage())
                            .build()
            );
        } catch (GenericException exception) {
            return ResponseEntity.ok(
                    Response.builder()
                            .responseTime(LocalDateTime.now())
                            .status(HttpStatus.FORBIDDEN)
                            .statusCode(HttpStatus.FORBIDDEN.value())
                            .message(exception.getMessage())
                            .build()
            );
        }
    }

    @PutMapping(value = "/update-setting/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            operationId = "update-setting",
            summary = "To update your existing settings info, Call this API",
            description = "updateSetting method is HTTP PUT mapping so to modify data in database."
    )
    public ResponseEntity<Response> updateSetting(@RequestBody @Valid SettingDto settingDto, @PathVariable("id") @Valid String id) {
        try {
            Setting setting = settingSvc.updateSetting(id, settingDto);
            if (setting.getType().equals(SettingType.CRAWL)) {
                crawlSettingSingleton.setUpdatedValuesFromSetting(setting);
            } else if (setting.getType().equals(SettingType.ROBOT_TXT)) {
                robotTxtSettingSingleton.setUpdatedValuesFromSetting(setting);
            }
            return ResponseEntity.ok(
                    Response.builder()
                            .responseTime(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("Setting updated successfully!")
                            .data(Collections.singletonMap("website", setting))
                            .build()
            );
        } catch (ValidationException | GenericException | IllegalArgumentException exception) {
            return ResponseEntity.ok(
                    Response.builder()
                            .responseTime(LocalDateTime.now())
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message(exception.getMessage())
                            .build()
            );
        } catch (IllegalAccessException exception) {
            return ResponseEntity.ok(
                    Response.builder()
                            .responseTime(LocalDateTime.now())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message(exception.getMessage())
                            .build()
            );
        }
    }

    @DeleteMapping(value = "/delete-setting/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            operationId = "delete-setting",
            summary = "To remove your setting for crawling, Call this API",
            description = "deleteSetting method is HTTP DELETE mapping so to remove data from database."
    )
    public ResponseEntity<Response> deleteSetting(@PathVariable("id") @Valid String id) {
        try {
            return ResponseEntity.ok(
                    Response.builder()
                            .responseTime(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("Setting removed successfully!")
                            .data(Collections.singletonMap("settings", settingSvc.deleteSetting(id)))
                            .build()
            );
        } catch (GenericException | IllegalArgumentException exception) {
            return ResponseEntity.ok(
                    Response.builder()
                            .responseTime(LocalDateTime.now())
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message(exception.getMessage())
                            .build()
            );
        }
    }
}