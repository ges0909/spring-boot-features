package com.valantic.sti.apps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TriggerController {

    private static final Logger logger = LoggerFactory.getLogger(TriggerController.class);

    @GetMapping("/trigger")
    @ResponseBody
    public String trigger(@RequestParam final String ref) {
        logger.info("'{}' triggered", ref);
        return "triggered";
    }
}