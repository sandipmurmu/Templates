package restapi;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.api.rest.RestApiService;
import com.api.rest.representation.Customer;
import com.api.rest.representation.Page;
import com.api.rest.representation.PageLinks;
import com.api.rest.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;



import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HandshakeCompletedListener;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.halLinks;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.atomLinks;
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
		Customer req = new Customer("0", "sandip", "murmu", "male");
		
		doNothing().when(cs).create(req);
		this.mockMvc.perform(post("/customers/")
                .content(this.objectMapper.writeValueAsString(req))
                .contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andDo(this.documentHandler.document( requestFields(
							fieldWithPath("id").description(" customer id"),
							fieldWithPath("first_name").description("first name of customer"),
							fieldWithPath("last_name").description("last name of customer"),
							fieldWithPath("gender").description("either male, female, others")
						)
				));
	}
	
	@Test
	public void getCustomerTest() throws Exception{
		
		Customer resp = new Customer("2","sandip","murmu","male");
		when(cs.getCustomer(2)).thenReturn(resp);
		this.mockMvc.perform(get("/customers/{id}", 2)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andDo(this.documentHandler.document(
						pathParameters(
								parameterWithName("id").description("customer id")
						),
						responseFields(
								fieldWithPath("id").description(" customer id"),
								fieldWithPath("first_name").description("first name of customer"),
								fieldWithPath("last_name").description("last name of customer"),
								fieldWithPath("gender").description("either male, female, others")	
					)
				));
		
	}
	
	
	@Test
	public void getListTest() throws Exception{
		Customer c1 = new Customer("2", "sandip", "murmu", "male");
		Customer c2 = new Customer("1", "nicole", "kelly", "female");
		List<Customer> customers = new ArrayList<>();
		customers.add(c1);
		customers.add(c2);
		PageLinks<List<Customer>> pageCustomers = new PageLinks<>(customers,4);
		
		when(cs.getList()).thenReturn(pageCustomers);
		this.mockMvc.perform(get("/customers/list")
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andDo(this.documentHandler.document(
							links(halLinks(),
									linkWithRel("pages").description("the customer's resource")
							),
							responseFields(
									fieldWithPath("_embedded.customerList[].id").description(" customer id"),
									fieldWithPath("_embedded.customerList[].first_name").description("first name of customer"),
									fieldWithPath("_embedded.customerList[].last_name").description("last name of customer"),
									fieldWithPath("_embedded.customerList[].gender").description("either male, female, others"),
									fieldWithPath("_links").description("Links")
							)
							
					)
				);
		
	}
	
	@Test
	public void getPageTest() throws Exception{
		Customer c1 = new Customer("2", "sandip", "murmu", "male");
		Customer c2 = new Customer("1", "nicole", "kelly", "female");
		List<Customer> customers = new ArrayList<>();
		customers.add(c1);
		customers.add(c2);
		Page<List<Customer>> page = new Page<>(customers);
		
		when(cs.getPage(1)).thenReturn(page);
		this.mockMvc.perform(get("/customers/pages/{page}", 1)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andDo(this.documentHandler.document(
							pathParameters(
									parameterWithName("page").description("page number")
							)))
					.andDo(this.documentHandler.document(
							responseFields(
									fieldWithPath("page[].id").description(" customer id"),
									fieldWithPath("page[].first_name").description("first name of customer"),
									fieldWithPath("page[].last_name").description("last name of customer"),
									fieldWithPath("page[].gender").description("either male, female, others")
									//fieldWithPath("_links").description("Links")
							)
							
					)
				);
		
	}
}
