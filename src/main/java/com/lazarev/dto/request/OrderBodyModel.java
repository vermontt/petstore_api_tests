package com.lazarev.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(exclude = "id")
public class OrderBodyModel {
	private String id;
	private String petId;
	private Integer quantity;
	private String shipDate;
	private String status;
	private Boolean complete;
}
