package br.com.crosscutting.builders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import br.com.fiap.dreamcontrol.dtos.PaginationResponseDTO;
import br.com.fiap.dreamcontrol.models.Registro;

public class PaginationResponseDTOBuilder {

    private List<Registro> content = List.of(
        new Registro(LocalDate.parse("2023-04-03"), LocalTime.parse("08:00:00")),
        new Registro(LocalDate.parse("2023-04-04"), LocalTime.parse("09:45:10"))
    );
        
    private int number = 0;
    private long totalElements = 2;
    private int totalPages = 1;
    private boolean first = true;
    private boolean last = true;
    
    public PaginationResponseDTOBuilder withContent(List<Registro> registros)
    {
        content = registros;
        return this;
    }

    public PaginationResponseDTOBuilder withTotalElements(long totalElements)
    {
        this.totalElements = totalElements;
        return this;
    }

    public PaginationResponseDTOBuilder withTotalPages(int totalPages)
    {
        this.totalPages = totalPages;
        return this;
    }

    public PaginationResponseDTOBuilder withFirst(boolean isFirst)
    {
        first = isFirst;
        return this;
    }

    public PaginationResponseDTOBuilder withLast(boolean isLast)
    {
        last = isLast;
        return this;
    }

    public PaginationResponseDTO build()
    {
        return new PaginationResponseDTO(content, number, totalElements, totalPages, first, last);
    }
}
