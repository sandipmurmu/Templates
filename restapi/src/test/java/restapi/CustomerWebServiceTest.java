package restapi;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.api.rest.RestApiService;
import com.api.rest.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;



import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;
import java.util.Map;



import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=RestApiService.class)
@WebAppConfiguration
public class CustomerWebServiceTest {
	
	
	@Rule
	public final JUnitRestDocumentation restDocumentation 
		= new JUnitRestDocumentation("target/generated-snippets");
	
	private RestDocumentationResultHandler documentHandler;
	
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private WebApplicationContext context;
	
	
	private MockMvc mockMvc;

	@MockBean
	private CustomerService cs;
	
	
	@Before
	public void setup(){
		
		this.documentHandler = document("{method-name}",
				  preprocessRequest(prettyPrint()),
			      preprocessResponse(prettyPrint()));
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(documentationConfiguration(this.restDocumentation))
				.alwaysDo(this.documentHandler)
				.build();
	}
	
	
	@Test
	public void createCustomerTest() throws Exception{
		Map<String, String> req = new HashMap<>();
		req.put("first_name", "sandip");
		req.put("last_name", "murmm");
		req.put("gender", "male");
		
		Map<String,String> resp = new HashMap<>();
		resp.put("customer_id", "2");
		
		/*this.documentHandler.snippets(
				requestFields(
						fieldWithPath("first_name").description("first name of customer"),
						fieldWithPath("last_name").description("last name of customer"),
						fieldWithPath("gender").description("either male, female, others")
							
			), 
			responseFields(
					fieldWithPath("customer_id").description("customer id created")
			)
		);*/
		
		
		
		when(cs.create(req)).thenReturn(resp);
		this.mockMvc.perform(post("/customers/")
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(req))
                .contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andDo(this.documentHandler.document( requestFields(
						fieldWithPath("first_name").description("first name of customer"),
						fieldWithPath("last_name").description("last name of customer"),
						fieldWithPath("gender").description("either male, female, others")
						),
						responseFields(
								fieldWithPath("customer_id").description("customer id created")
				)));
	}
	
	
	
}
