package br.gov.serpro.rtc.config.xml;

import static jakarta.xml.bind.JAXBContext.newInstance;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.gov.serpro.rtc.api.model.xml.bpe.normal.InfBPe;
import br.gov.serpro.rtc.api.model.xml.cte.normal.InfCte;
import br.gov.serpro.rtc.api.model.xml.nf3e.InfNF3E;
import br.gov.serpro.rtc.api.model.xml.nfe.InfNFe;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

/**
 * Configuração de JAXBContext para serialização XML.
 * 
 * IMPORTANTE: JAXBContext é thread-safe e pode ser compartilhado. Marshaller
 * NÃO é thread-safe e deve ser criado para cada operação.
 */
@Configuration
public class JAXBContextConfig {

    @Bean(name = "jaxbInfCteContext")
    JAXBContext jaxbInfCteContext() throws JAXBException {
        return newInstance(InfCte.class);
    }

    @Bean(name = "jaxbInfCteSimpContext")
    JAXBContext jaxbInfCteSimpContext() throws JAXBException {
        return newInstance(br.gov.serpro.rtc.api.model.xml.cte.simplificado.InfCte.class);
    }

    @Bean(name = "jaxbInfNfeContext")
    JAXBContext jaxbInfNfeContext() throws JAXBException {
        return newInstance(InfNFe.class);
    }

    @Bean(name = "jaxbInfNfceContext")
    JAXBContext jaxbInfNfceContext() throws JAXBException {
        return newInstance(br.gov.serpro.rtc.api.model.xml.nfce.InfNFe.class);
    }

    @Bean(name = "jaxbInfBPeContext")
    JAXBContext jaxbInfBPeContext() throws JAXBException {
        return newInstance(InfBPe.class);
    }
    
    @Bean(name = "jaxbInfBPeTMContext")
    JAXBContext jaxbInfBPeTMContext() throws JAXBException {
        return newInstance(br.gov.serpro.rtc.api.model.xml.bpe.tm.InfBPe.class);
    }

    @Bean(name = "jaxbInfNF3EContext")
    JAXBContext jaxbInfNF3EContext() throws JAXBException {
        return newInstance(InfNF3E.class);
    }
}