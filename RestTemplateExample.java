import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;
import org.springframework.web.util.UriComponentsBuilder;

final class RestTemplateExample {

	static RestTemplate rest = new RestTemplate();

	/**
	 * Calling get with no params
	 */
	public static void callingGet() {

		try {

			String url = "http://someapiurl/get/user/all";
			HttpHeaders headers = new HttpHeaders();
			headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

			HttpEntity<?> entity = new HttpEntity<>(headers);

			String response = rest.getForObject(url, String.class, entity);
			System.out.println(response);
		} catch (

		HttpClientErrorException e) {
			// Handle error
		} catch (HttpServerErrorException e) {
			// Handle error
		} catch (UnknownHttpStatusCodeException e) {
			// Handle error
		} catch (Exception e) {
			// Handle error
		}

	}

	/**
	 * Calling a get api with query params
	 */
	public static void callingGetQuery(String username) {

		try {

			String url = "http://someapiurl/get/user";
			HttpHeaders headers = new HttpHeaders();
			headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("username", username);

			HttpEntity<?> entity = new HttpEntity<>(headers);
			HttpEntity<String> response = rest.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

			System.out.println(response);

		} catch (HttpClientErrorException e) {
			// Handle error
		} catch (HttpServerErrorException e) {
			// Handle error
		} catch (UnknownHttpStatusCodeException e) {
			// Handle error
		} catch (Exception e) {
			// Handle error
		}

	}

	/**
	 * Calling a get api with path params
	 */
	public static void callingGetPath(String username) {

		try {

			String url = "http://someapiurl/get/user/{username}";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<?> entity = new HttpEntity<>(headers);

			Map<String, String> param = new HashMap<String, String>();
			param.put("username", username);

			HttpEntity<String> response = rest.exchange(url, HttpMethod.GET, entity, String.class, param);

			System.out.println(response);

		} catch (HttpClientErrorException e) {
			// Handle error
		} catch (HttpServerErrorException e) {
			// Handle error
		} catch (UnknownHttpStatusCodeException e) {
			// Handle error
		} catch (Exception e) {
			// Handle error
		}

	}

	/**
	 * Calling Post api with data in form-urlencoded
	 * 
	 * @param username
	 * @param passwordHashed
	 */
	public static void callingPostWithFormEncode(String username, String passwordHashed) {
		try {

			String url = "http://someapiurl/add/user";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

			MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
			formData.add("username", username);
			formData.add("password", passwordHashed);

			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(formData,
					headers);

			String response = rest.postForObject(url, request, String.class);

			System.out.println(response);

		} catch (HttpClientErrorException e) {
			// Handle error
		} catch (HttpServerErrorException e) {
			// Handle error
		} catch (UnknownHttpStatusCodeException e) {
			// Handle error
		} catch (Exception e) {
			// Handle error
		}
	}

	/**
	 * Calling Post api with data in json body
	 * 
	 * @param username
	 * @param passwordHashed
	 */
	public static void callingPostWithJsonRawData(String username, String passwordHashed) {

		try {

			String url = "http://someapiurl/add/user";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			RestUserBean userBean = new RestUserBean(username, passwordHashed);

			HttpEntity<RestUserBean> request = new HttpEntity<>(userBean, headers);

			RestUserResponse response = rest.postForObject(url, request, RestUserResponse.class);

			System.out.println(response != null ? response.getMessage() : "null");

		} catch (HttpClientErrorException e) {
			// Handle error
		} catch (HttpServerErrorException e) {
			// Handle error
		} catch (UnknownHttpStatusCodeException e) {
			// Handle error
		} catch (Exception e) {
			// Handle error
		}

	}

	/**
	 * Calling Put api
	 * 
	 * @param username
	 * @param passwordHashed
	 */
	public static void callingPutApi(String username, String passwordHashed) {

		try {

			String url = "http://someapiurl/update/user/{username}";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			Map<String, String> param = new HashMap<String, String>();
			param.put("username", username);

			RestUserBean userBean = new RestUserBean(passwordHashed);
			HttpEntity<RestUserBean> request = new HttpEntity<>(userBean, headers);

			HttpEntity<RestUserResponse> response = rest.exchange(url, HttpMethod.PUT, request, RestUserResponse.class, param);

			System.out.println(response != null ? response.getBody().getMessage() : "null");

		} catch (HttpClientErrorException e) {
			// Handle error
		} catch (HttpServerErrorException e) {
			// Handle error
		} catch (UnknownHttpStatusCodeException e) {
			// Handle error
		} catch (Exception e) {
			// Handle error
		}
	}
	
	/** 
	 * Calling Delete api
	 * @param username
	 */
	public static void callingDeleteApi(String username) {
		
		try {
			
			String url = "http://someapiurl/delete";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<?> entity = new HttpEntity<>(headers);
			
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
				    .queryParam("userName", username);
			
			rest.delete(builder.toUriString(), entity);
			
		} catch (HttpClientErrorException e) {
			// Handle error
		} catch (HttpServerErrorException e) {
			// Handle error
		} catch (UnknownHttpStatusCodeException e) {
			// Handle error
		} catch (Exception e) {
			// Handle error
		}
	}

}
