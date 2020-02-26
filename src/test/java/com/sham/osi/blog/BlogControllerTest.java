package com.sham.osi.blog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import jdk.nashorn.internal.ir.annotations.Ignore;

// TODO: Make tests success work

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BlogControllerTest {

	private static final String CONTEXT_PATH = "/posts";

	@BeforeClass
	public static void initialSetup() {
		BlogFixture.loadTemplates();
	}

	// @Autowired
	// private ObjectMapper objectMapper;

	// @Mock
	// @InjectMocks
	// private BlogViewerServiceImpl blogViewerServiceImpl;

	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setup() {
		// MockitoAnnotations.initMocks(this);
		// final List<Blog> values = Fixture.from(Blog.class).gimme(1,
		// LabelFixture.DEFAULT);
		// Mockito.when(this.blogViewerServiceImpl.getAllBlogs()).thenReturn(values);
	}

	@Test
	public void testGetAllFailureNoContent() throws Exception {
		final MvcResult andReturn = this.mockMvc.perform(MockMvcRequestBuilders.get(CONTEXT_PATH))
				.andExpect(MockMvcResultMatchers.status().isNoContent()).andReturn();
		assertNotNull(andReturn);
		assertEquals(new ByteArrayOutputStream().toString(), andReturn.getResponse().getContentAsString());
	}

	@Test
	public void testCreateFailureNoContent() throws Exception {
		this.mockMvc
		.perform(
				MockMvcRequestBuilders.post(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON).content("{}"))
		.andExpect(MockMvcResultMatchers.status().isUnauthorized()).andReturn();
	}

	@Ignore
	@Test
	public void testCreateSuccessCreated() throws Exception {
		this.mockMvc
		.perform(
				MockMvcRequestBuilders.post(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON).content("{}"))
		.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	public void testGetByIdFailureNoContent() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get(CONTEXT_PATH.concat("/1")))
		.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
}
