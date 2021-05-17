package com.hiking.exception;

import com.hiking.util.Consts;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @developer -- ilkercolakoglu
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = Consts.NO_TOUR_FOUND)
public class TourNotFoundException extends Exception {
    static final long serialVersionUID = -3387516993224229948L;


    public TourNotFoundException(String message)
    {
        super(message);
    }
}
