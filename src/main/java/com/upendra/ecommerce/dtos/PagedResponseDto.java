package com.upendra.ecommerce.dtos;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(NON_NULL)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagedResponseDto<T> {

	private List<T> list;
	private Integer page;
	private Integer size;
	private Long totalElements;

}