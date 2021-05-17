package com.hiking.exception;

import com.hiking.util.Consts;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @developer -- ilkercolakoglu
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = Consts.AGE_RULE_EXCEPTION)
public class ViolateAgeRulesException extends Exception {
    static final long serialVersionUID = -3387516993224229948L;


    public ViolateAgeRulesException(String message)
    {
        super(message);
    }
}
