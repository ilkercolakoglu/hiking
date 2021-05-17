package com.hiking.mapper;

import com.hiking.dto.BookingDTO;
import com.hiking.entity.Booking;
import org.mapstruct.Mapper;

import java.awt.print.Book;

/**
 * @developer -- ilkercolakoglu
 */

@Mapper
public interface BookingMapper extends BaseMapper<Booking, BookingDTO>{

}
