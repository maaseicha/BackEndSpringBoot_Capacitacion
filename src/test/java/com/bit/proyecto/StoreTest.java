package com.bit.proyecto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.bit.proyecto.rest.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StoreTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getCatalogoFilterByGeneroExist() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String body = this.mockMvc.perform(get("/catalogo?genero=M")).andReturn().getResponse().getContentAsString();
		Response response = mapper.readValue(body,Response.class);
		Assert.assertEquals(response.getCode(),"200");
		Assert.assertTrue(response.getStatus());

		String data = mapper.writeValueAsString(response.getData());
		List<?> dtoList = mapper.readValue(data, ArrayList.class);
		String[] dtoShoe = dtoList.get(1).toString().split(",");
		Assert.assertEquals(dtoShoe[3].substring(8),"M");
	}
	@Test
	public void getCatalogoFilterByGeneroNotFound() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String body = this.mockMvc.perform(get("/catalogo?genero=X")).andReturn().getResponse().getContentAsString();
		Response response = mapper.readValue(body,Response.class);
		Assert.assertEquals(response.getCode(),"404");
		Assert.assertFalse(response.getStatus());
		Assert.assertEquals(response.getData(),null);
	}
	@Test
	public void getCatalogoPaginateSize() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String body = this.mockMvc.perform(get("/catalogo?page=0&size=3")).andReturn().getResponse().getContentAsString();
		Response response = mapper.readValue(body,Response.class);
		Assert.assertEquals(response.getCode(),"200");
		Assert.assertTrue(response.getStatus());

		String data = mapper.writeValueAsString(response.getData());
		List<?> dtoList = mapper.readValue(data, ArrayList.class);
		Assert.assertEquals(dtoList.size(),3);
	}
	@Test
	public void getCatalogoOrderASC() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String body = this.mockMvc.perform(get("/catalogo")).andReturn().getResponse().getContentAsString();
		Response response = mapper.readValue(body,Response.class);
		Assert.assertEquals(response.getCode(),"200");
		Assert.assertTrue(response.getStatus());

		String data = mapper.writeValueAsString(response.getData());
		List<?> dtoList = mapper.readValue(data, ArrayList.class);
		String[] dtoShoe = dtoList.get(0).toString().split(",");
		Assert.assertEquals(dtoShoe[0].substring(6),"ZAP-1");
	}
	@Test
	public void getCatalogoOrderDESC() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String body = this.mockMvc.perform(get("/catalogo?order=desc")).andReturn().getResponse().getContentAsString();
		Response response = mapper.readValue(body,Response.class);
		Assert.assertEquals(response.getCode(),"200");
		Assert.assertTrue(response.getStatus());

		String data = mapper.writeValueAsString(response.getData());
		List<?> dtoList = mapper.readValue(data, ArrayList.class);
		String[] dtoShoe = dtoList.get(0).toString().split(",");
		Assert.assertEquals(dtoShoe[0].substring(6),"ZAP-10");
	}
	@Test
	public void addCarritoCodeShoeNotFound() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String body = this.mockMvc.perform(get("/carrito?codeShoe=ZAP-26&amount=4&codePerson=3")).andReturn().getResponse().getContentAsString();
		Response response = mapper.readValue(body,Response.class);
		Assert.assertEquals(response.getMessage(),"The product was not found.");
		Assert.assertEquals(response.getCode(),"404");
		Assert.assertFalse(response.getStatus());
		Assert.assertEquals(response.getData(),null);
	}
	@Test
	public void addCarritoCodePersonNotFound() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String body = this.mockMvc.perform(get("/carrito?codeShoe=ZAP-2&amount=4&codePerson=3")).andReturn().getResponse().getContentAsString();
		Response response = mapper.readValue(body,Response.class);
		Assert.assertEquals(response.getMessage(),"The person was not found.");
		Assert.assertEquals(response.getCode(),"404");
		Assert.assertFalse(response.getStatus());
		Assert.assertEquals(response.getData(),null);
	}
	@Test
	public void addCarritoAmountLessEqualToZero() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String body = this.mockMvc.perform(get("/carrito?codeShoe=ZAP-2&amount=0&codePerson=1")).andReturn().getResponse().getContentAsString();
		Response response = mapper.readValue(body,Response.class);
		Assert.assertEquals(response.getMessage(),"Enter a quantity greater than zero and less than the stock of the shoe(9)");
		Assert.assertEquals(response.getCode(),"400");
		Assert.assertFalse(response.getStatus());
		Assert.assertEquals(response.getData(),null);
	}
	@Test
	public void addCarritoAmountGreaterThanStock() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String body = this.mockMvc.perform(get("/carrito?codeShoe=ZAP-2&amount=11&codePerson=1")).andReturn().getResponse().getContentAsString();
		Response response = mapper.readValue(body,Response.class);
		Assert.assertEquals(response.getMessage(),"Enter a quantity greater than zero and less than the stock of the shoe(9)");
		Assert.assertEquals(response.getCode(),"400");
		Assert.assertFalse(response.getStatus());
		Assert.assertEquals(response.getData(),null);
	}
	@Test
	public void addCarritoSuccessfully() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String body = this.mockMvc.perform(get("/carrito?codeShoe=ZAP-2&amount=1&codePerson=1")).andReturn().getResponse().getContentAsString();
		Response response = mapper.readValue(body,Response.class);
		Assert.assertEquals(response.getMessage(),"Product added to cart successfully");
		Assert.assertEquals(response.getCode(),"200");
		Assert.assertTrue(response.getStatus());
	}
	@Test
	public void addCarritoGreaterThanAmountCarrito() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String body = this.mockMvc.perform(get("/carrito?codeShoe=ZAP-1&amount=1&codePerson=1")).andReturn().getResponse().getContentAsString();
		body = this.mockMvc.perform(get("/carrito?codeShoe=ZAP-1&amount=5&codePerson=1")).andReturn().getResponse().getContentAsString();
		Response response = mapper.readValue(body,Response.class);
		Assert.assertEquals(response.getMessage(),"There are (4) shoes in stock, for the shoe with code ZAP-1");
		Assert.assertEquals(response.getCode(),"400");
		Assert.assertFalse(response.getStatus());
	}

	//validate buy test
	@Test
	public void buyNoItemsCarrito() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String body = this.mockMvc.perform(post("/buy")).andReturn().getResponse().getContentAsString();
		Response response = mapper.readValue(body,Response.class);
		Assert.assertEquals(response.getMessage(),"Add elements to the cart at the link: http://localhost:8080/carrito sending shoe code, quantity and customer code as parameters");
		Assert.assertEquals(response.getCode(),"404");
		Assert.assertFalse(response.getStatus());
	}
	@Test
	public void buySuccessfully() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String body = this.mockMvc.perform(get("/carrito?codeShoe=ZAP-3&amount=1&codePerson=1")).andReturn().getResponse().getContentAsString();
		body = this.mockMvc.perform(post("/buy")).andReturn().getResponse().getContentAsString();
		Response response = mapper.readValue(body,Response.class);
		Assert.assertEquals(response.getMessage(),"Buy successfully");
		Assert.assertEquals(response.getCode(),"200");
		Assert.assertTrue(response.getStatus());
	}

	//change status deliver
	@Test
	public void sendDeliverNotFound() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String body = this.mockMvc.perform(patch("/send?codeDeliver=10")).andReturn().getResponse().getContentAsString();
		Response response = mapper.readValue(body,Response.class);
		Assert.assertEquals(response.getMessage(),"The deliver was not found.");
		Assert.assertEquals(response.getCode(),"404");
		Assert.assertFalse(response.getStatus());
	}
	@Test
	public void sendDeliverSuccess() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String body = this.mockMvc.perform(patch("/send?codeDeliver=3")).andReturn().getResponse().getContentAsString();
		Response response = mapper.readValue(body,Response.class);
		Assert.assertEquals(response.getMessage(),"Order sent successfully");
		Assert.assertEquals(response.getCode(),"200");
		Assert.assertTrue(response.getStatus());

		String data = mapper.writeValueAsString(response.getData());
		String[] dtoShoe = data.split(",");
		Assert.assertEquals(dtoShoe[6].substring(10,17),"Enviado");
	}
}