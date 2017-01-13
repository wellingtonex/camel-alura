package br.com.caelum.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.xstream.XStreamDataFormat;
import org.apache.camel.impl.DefaultCamelContext;

import com.thoughtworks.xstream.XStream;

public class RotaNegociacoes {

	public static void main(String[] args) throws Exception {
		

		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				final XStream xstream = new XStream();
				xstream.alias("negociacao", Negociacao.class);
				
			    from("timer://negociacoes?fixedRate=true&delay=3s&period=360s")
			      .to("http4://argentumws.caelum.com.br/negociacoes")
			      .convertBodyTo(String.class)
			      .unmarshal(new XStreamDataFormat(xstream))
			      .split(body())
			      .log("${body}")
			    .end(); //só deixa explícito que é o fim da rota
			}
		});

		context.start();
		Thread.sleep(20000);
		context.stop();
	}
}
