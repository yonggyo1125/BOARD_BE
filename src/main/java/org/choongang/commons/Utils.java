package org.choongang.commons;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Utils {

    private final MessageSource messageSource;


    public Map<String, List<String>> getErrorMessages(Errors errors) {

        Map<String, List<String>> messages = errors.getFieldErrors()
                .stream()
                .collect(Collectors.toMap("", this::_getErrorMessages));

        return messages;
    }

    private List<String[]> _getErrorMessages(String[] codes) {
        return null;
    }
}
