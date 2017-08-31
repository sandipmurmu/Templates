package restapi;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;

import com.fasterxml.jackson.databind.ObjectMapper;


/*
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=StockPriceApp.class)
@WebAppConfiguration*/
public class StockPriceServiceTest {

	@Rule
	public final JUnitRestDocumentation restDocumentation 
		= new JUnitRestDocumentation("target/generated-snippets");
	
	private RestDocumentationResultHandler documentHandler;
	
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private WebApplicationContext context;
	
	
	
}
