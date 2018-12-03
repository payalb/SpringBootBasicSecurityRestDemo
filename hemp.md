Run Integration test only if unit tests work fine. Heavy
failsafeplugin to run Interation test with goal verify
surefire plugin to test unit tests

MVC tests are intended to cover just the controller piece of your application. HTTP requests and responses are mocked so the real connections are not created. On the other hand, when you use @SpringBootTest, all the configuration for the web application context is loaded and the connections are going through the real web server. In that case, you don’t use the MockMvc bean but a standard RestTemplate instead (or the new alternative TestRestTemplate).
So, when should we choose one or the other? @WebMvcTest is intended to test unitarily the controller from the server side. @SpringBootTest, on the other hand, should be used for integration tests, when you want to interact with the application from the client side.
That doesn’t mean that you can’t use mocks with @SpringBootTest; if you’re writing an integration test, that could still be necessary. In any case, it’s better not to use it just for a simple controller’s unit test.



Following logout implementation is one way to logout programatically.You can also configure logout in SpringSecurityConfig.java.You can use any one of them but not both.Once basic authentication is successfull, browser automatically sends basic auth header in every request following successfull authentication.Hence, if you are using this authentication mechanism in a browser based application, it is required to clear the Basic auth cache in the browser too after logout.
@RequestMapping(value="/logmeout", method = RequestMethod.POST)
public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
Authentication auth = SecurityContextHolder.getContext().getAuthentication();
if (auth != null){
new SecurityContextLogoutHandler().logout(request, response, auth);
}
return "redirect:/login";
}
Handling Cross Origin Requests
To handle Cross Origin requests add following configuration in the bean definition.
@Bean
public WebMvcConfigurer corsConfigurer() {
	return new WebMvcConfigurerAdapter() {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*")
				.allowedHeaders("*");
		}
	};
}


Shown below is the sample code for preparing the header.
String plainClientCredentials="myusername:mypassword";
String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));
 
HttpHeaders headers = getHeaders();
headers.add("Authorization", "Basic " + base64ClientCredentials);
which may in turn produce something like:
Authorization : Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0...
This header will be sent with ech request. Since Credentials [Base 64 encoded, not even encrypted] are sent with each request, they can be compromised. One way to prevent this is using HTTPS in conjunction with Basic Authentication.


Basic Authentication & Spring Security
With two steps, you can enable the Basic Authentication in Spring Security Configuration.
1. Configure httpBasic : Configures HTTP Basic authentication. [http-basic in XML]
2. Configure authentication entry point with BasicAuthenticationEntryPoint : In case the Authentication fails [invalid/missing credentials], this entry point will get triggered. It is very important, because we don’t want [Spring Security default behavior] of redirecting to a login page on authentication failure [ We don’t have a login page].