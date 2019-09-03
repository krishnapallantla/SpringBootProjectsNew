package com.krishna.springassignment;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.krishna.springassignment.boot.controller.rest.WebController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=WebController.class)
@ContextConfiguration(classes = {com.krishna.springassignment.boot.service.NumToWordImpl.class
		,com.krishna.springassignment.boot.controller.rest.WebController.class
		})
@WebAppConfiguration
public class SpringAssignmentApplicationTests {

	private MockMvc mockMvc;
	
	@InjectMocks
	private WebController webController;
	
	  @Before
	    public void setUp() throws Exception {
		  mockMvc = MockMvcBuilders.standaloneSetup(new WebController()).build();
	    }
	 
	@Test
	public void testGetW() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/n"))
               .andExpect(MockMvcResultMatchers.status().isOk())
		       .andExpect(MockMvcResultMatchers.content().string("test"));
	}

	/*Following is a sanity test for single word*/
	@Test
	public void testSanity() throws Exception {
	
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/num/567")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

		mockMvc.perform(MockMvcRequestBuilders.get("/api/num/567").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"num\":\"567\",\"word\":\"five hundred sixty seven\"}")));
	}
	
	/*Following is a sanity test for Multiple word*/
	/*Our code can also convert multiple numbers separated by : to words respectively*/
	@Test
	public void testSanityMultipleInputs() throws Exception {
		
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/nums/567:789:999999999")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

		mockMvc.perform(MockMvcRequestBuilders.get("/api/nums/567:789:999999999").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"num\":\"567\",\"word\":\"five hundred sixty seven\"}"
                		                            + ",{\"num\":\"789\",\"word\":\"seven hundred eighty nine\"}"
                		                            + ",{\"num\":\"999999999\",\"word\":\"nine hundred ninety nine million nine hundred ninety nine thousand nine hundred ninety nine\"}]")));
	}
	
	
	/*Test for invalid numbers with asteric char*/
	@Test
	public void testInvalidNumberWithAsterik() throws Exception {
		
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/nums/567*8")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

		mockMvc.perform(MockMvcRequestBuilders.get("/api/nums/567*8").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"num\":\"567*8\",\"word\":\"not a valid number\"}]")));
	}
	
	/*Test for invalid numbers with special characters*/
	@Test
	public void testInvalidNumberWithSpecailChar() throws Exception {
		
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/nums/5@6_7-$8")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

		mockMvc.perform(MockMvcRequestBuilders.get("/api/nums/5@6_7-$8").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"num\":\"5@6_7-$8\",\"word\":\"not a valid number\"}]")));
	}
	
	/*Test for negative number*/
	@Test
	public void testNegativeNumber() throws Exception {
		
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/nums/-555557")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

		mockMvc.perform(MockMvcRequestBuilders.get("/api/nums/-555557").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"num\":\"-555557\",\"word\":\"Input is expected between 0 and 999999999\"}]")));
	}
	
	/*Test for closest not allowed higher boundary*/
	@Test
	public void testNotAllowedGreaterNumber() throws Exception {
		
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/nums/1000000000")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

		mockMvc.perform(MockMvcRequestBuilders.get("/api/nums/1000000000").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"num\":\"1000000000\",\"word\":\"Input is expected between 0 and 999999999\"}]")));
	}

	/*Test for closest not allowed lower boundary -1 */
	@Test
	public void testNotAllowedLeastNumber() throws Exception {
		
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/nums/-1")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

		mockMvc.perform(MockMvcRequestBuilders.get("/api/nums/-1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"num\":\"-1\",\"word\":\"Input is expected between 0 and 999999999\"}]")));
	}
	
	
	/*Test for highest allowed boundary 999999999*/
	@Test
	public void testSanityHighestNumber() throws Exception {
		
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/nums/999999999")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

		mockMvc.perform(MockMvcRequestBuilders.get("/api/nums/999999999").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"num\":\"999999999\",\"word\":\"nine hundred ninety nine million nine hundred ninety nine thousand nine hundred ninety nine\"}]")));
	}
	
	/*Test for least allowed boundary 0*/
	@Test
	public void testSanityLeastNumber() throws Exception {
		
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/nums/0")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

		mockMvc.perform(MockMvcRequestBuilders.get("/api/nums/0").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"num\":\"0\",\"word\":\"zero\"}]")));
	}
	
	/*Test for least allowed boundary 0*/
	/*
	 * @Test public void testNonIntegerNumber() throws Exception {
	 * 
	 * MvcResult r = mockMvc.perform(MockMvcRequestBuilders
	 * .get("/api/nums/123.3434") .accept(MediaType.APPLICATION_JSON)).andReturn();
	 * 
	 * mockMvc.perform(MockMvcRequestBuilders.get("/api/nums/123.3434").accept(
	 * MediaType.APPLICATION_JSON)) .andExpect(status().isOk())
	 * .andExpect(content().string(
	 * equalTo("[{\"num\":\"123.3434\",\"word\":\"not a valid number\"}]"))); }
	 */
	
	/*Test for leading zeroes which are not considered*/
	@Test
	public void testUnWantedZeroes() throws Exception {
		
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/nums/000999999999")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

		mockMvc.perform(MockMvcRequestBuilders.get("/api/nums/000999999999").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"num\":\"000999999999\",\"word\":\"nine hundred ninety nine million nine hundred ninety nine thousand nine hundred ninety nine\"}]")));
	}
	
	/*Test for multiple inputs having above all scenarios*/
	@Test
	public void testAllInOne() throws Exception {
		
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/nums/-1:0:999999999:1000000000:7893456:-3743:0004500:ABCD:!*009")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

		mockMvc.perform(MockMvcRequestBuilders.get("/api/nums/-1:0:999999999:1000000000:7893456:-3743:0004500:ABCD:!*009").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"num\":\"-1\",\"word\":\"Input is expected between 0 and 999999999\"}"
                		+ ",{\"num\":\"0\",\"word\":\"zero\"},{\"num\":\"999999999\",\"word\":\"nine hundred ninety nine million nine hundred ninety nine thousand nine hundred ninety nine\"},{\"num\":\"1000000000\",\"word\":\"Input is expected between 0 and 999999999\"},{\"num\":\"7893456\",\"word\":\"seven million eight hundred ninety three thousand four hundred fifty six\"},{\"num\":\"-3743\",\"word\":\"Input is expected between 0 and 999999999\"},{\"num\":\"0004500\",\"word\":\"four thousand five hundred\"},{\"num\":\"ABCD\",\"word\":\"not a valid number\"}"
                		+ ",{\"num\":\"!*009\",\"word\":\"not a valid number\"}]")));
	}

	/*Test for invalid number(alphabets)*/
	@Test
	public void testInvalidNotNumber() throws Exception {
		
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/nums/abc")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

		mockMvc.perform(MockMvcRequestBuilders.get("/api/nums/abc").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"num\":\"abc\",\"word\":\"not a valid number\"}]")));
	}

	/*Test for wrong path*/
	/*
	 * @Test public void testInvalidPath() throws Exception {
	 * 
	 * MvcResult r = mockMvc.perform(MockMvcRequestBuilders
	 * .get("/api/nums/abc/def") .accept(MediaType.APPLICATION_JSON)).andReturn();
	 * 
	 * mockMvc.perform(MockMvcRequestBuilders.get("/api/nums/abc/def").accept(
	 * MediaType.APPLICATION_JSON)) //.andExpect(status().isBadRequest())
	 * .andExpect(status().isServiceUnavailable());
	 * 
	 * }
	 */
}
