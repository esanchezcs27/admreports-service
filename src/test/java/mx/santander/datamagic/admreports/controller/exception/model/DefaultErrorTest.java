package mx.santander.datamagic.admreports.controller.exception.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;

import mx.santander.datamagic.admreports.util.ErrorEnum;


@ExtendWith(MockitoExtension.class)
class DefaultErrorTest {

	@Test
	void dafaultError_get() throws JsonProcessingException {
		DefaultError defaultError = new DefaultError("code", "message", "level", "desc", "moreInfo");
		defaultError = new DefaultError(ErrorEnum.EXC_DUPLICADO);
		defaultError = new DefaultError(ErrorEnum.EXC_DUPLICADO, "moreInfo");
		DefaultErrorList defaultErrorList = new DefaultErrorList(defaultError);
		defaultErrorList.add(defaultError);
		List<DefaultError> list = new ArrayList<>();
		list.add(defaultError);
		defaultErrorList.setErrors(list);
		defaultErrorList.getErrors();
		defaultErrorList.toJsonString();
		assertNotNull(defaultErrorList);
		assertNotNull(defaultError.getCode());
		assertNotNull(defaultError.getMessage());
		assertNotNull(defaultError.getLevel());
		assertNotNull(defaultError.getDescription());
		assertNotNull(defaultError.getMoreInfo());
		
	}
}
