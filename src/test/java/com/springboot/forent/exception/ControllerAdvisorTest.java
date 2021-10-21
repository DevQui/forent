package com.springboot.forent.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(ControllerAdvisor.class)
class ControllerAdvisorTest {

	@Autowired
	ControllerAdvisor advisor;
	
	@Test
    void DataNotFoundExceptionTest() throws DataNotFoundException{
		ResponseEntity<Object> handled = advisor.handleDataNotFoundException(new DataNotFoundException(1),
                null);
		
        Assertions.assertEquals(handled.getStatusCode().value(), 404);
		 
    }
	
	@Test
    void NoDataFoundException() throws NoDataFoundException{
		ResponseEntity<Object> handled = advisor.handleNoDataFoundException(new NoDataFoundException(),
                null);
		
        Assertions.assertEquals(handled.getStatusCode().value(), 404);
		 
    }

	
	/*@Test
    void MethodArgumentNotValid() throws Exception{
		
		HttpHeaders header = null;
		HttpStatus status = null;
		
		BindingResult bindingResult = new MapBindingResult(Collections.singletonMap("a", "b"), "objectName");
		bindingResult.addError(new ObjectError("c", "d"));
		
		ResponseEntity<Object> handled = advisor.handleMethodArgumentNotValid(
				new MethodArgumentNotValidException(null, bindingResult),header, status, null);
		//System.out.println(handled.getBody);
        Assertions.assertNull(handled);
		 
    }*/
}
