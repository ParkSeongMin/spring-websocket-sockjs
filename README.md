# spring-websocket-sockjs

spring websocket
	- http://docs.spring.io/spring/docs/current/spring-framework-reference/html/websocket.html

	@Configuration
	@EnableWebSocket
	public class WebSocketConfig implements WebSocketConfigurer {
	
		@Bean
		public WebSocketHandler myHandler() {
			return new EchoWebSocketHandler();
		}
		
		@Override
		public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
			registry.addHandler(myHandler(), "websocket")
			.setAllowedOrigins("*")
			.withSockJS()
	//		.setClientLibraryUrl("http://172.10.12.150:8081/sockjs-1.1.1.js");
			.setClientLibraryUrl("http://172.10.12.137:8081/sockjs-1.1.1.js");
		}
	
	}

	public class EchoWebSocketHandler extends TextWebSocketHandler {

		@Override
		public void handleMessage(WebSocketSession session,
				WebSocketMessage<?> message) throws Exception {
	
			String payloadMessage = (String) message.getPayload();
			try {
	
				System.out.println("payload message : " + payloadMessage);
				session.sendMessage(new TextMessage(payloadMessage));
	
			} catch (JsonParseException jpe) {
				jpe.printStackTrace();
			} catch (JsonMappingException jme) {
				jme.printStackTrace();
			}
		}
	}
	
	@Configuration
	@EnableWebMvc
	public class WebConfig extends WebMvcAutoConfiguration {
	}

	public class IndexController {

		@RequestMapping("/")
		public String index() {
		   return "forward:index.html";
		}
		
	}

	spring boot로 생성 된 프로젝트 내 src/main/resources/static 하단에 웹 리소스가 위치한다.
		- https://spring.io/blog/2013/12/19/serving-static-web-content-with-spring-boot
		
	기본적으로 아래 항목들이 참조된다.
		/META-INF/resources/
		/resources/
		/static/
		/public/







sockjs
	- https://github.com/sockjs/sockjs-client
	
client code smaple
	// var sockjs = new SockJS(url, _reserved, options);
	
	var sock = new SockJS('http://mydomain.com/my_prefix');
	 sock.onopen = function() {
	     console.log('open');
	 };
	 sock.onmessage = function(e) {
	     console.log('message', e.data);
	 };
	 sock.onclose = function() {
	     console.log('close');
	 };
	
	 sock.send('test');
	 sock.close();
	 
server (string)
	- String to append to url for actual data connection. Defaults to a random 4 digit number.

transports (string OR array of strings)
	- Sometimes it is useful to disable some fallback transports. This option allows you to supply a list transports that may be used by SockJS. By default all available transports will be used.

sessionId (number OR function)
	- Both client and server use session identifiers to distinguish connections. If you specify this option as a number, SockJS will use its random string generator function to generate session ids that are N-character long (where N corresponds to the number specified by sessionId). When you specify this option as a function, the function must return a randomly generated string. Every time SockJS needs to generate a session id it will call this function and use the returned string directly. If you don't specify this option, the default is to use the default random string generator to generate 8-character long session ids.
