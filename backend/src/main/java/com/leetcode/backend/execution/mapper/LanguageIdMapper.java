package com.leetcode.backend.execution.mapper;

import com.leetcode.backend.problem.enums.Language;

import java.util.Map;

public class LanguageIdMapper {

    private static final Map<Language,Integer> LANGUAGE_MAP =
            Map.of(
                    Language.JAVA,62,
                    Language.PYTHON,71,
                    Language.JAVASCRIPT,63
            );

    public static Integer getLanguageId(
            Language language
    ) {

        return LANGUAGE_MAP.get(language);
    }
}
