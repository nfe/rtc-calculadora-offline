/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.exceptionhandler;

import java.net.URI;
import java.util.stream.Stream;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.gov.serpro.rtc.api.util.HttpUtils;
import br.gov.serpro.rtc.domain.service.exception.AliquotaAdRemNaoEncontradaException;
import br.gov.serpro.rtc.domain.service.exception.AliquotaReferenciaNaoEncontradaException;
import br.gov.serpro.rtc.domain.service.exception.CampoInvalidoException;
import br.gov.serpro.rtc.domain.service.exception.CaptchaException;
import br.gov.serpro.rtc.domain.service.exception.ClassificacaoTributariaNaoEncontradaException;
import br.gov.serpro.rtc.domain.service.exception.ClassificacaoTributariaNaoVinculadaSituacaoTributariaException;
import br.gov.serpro.rtc.domain.service.exception.ErroFaltaImplementacaoException;
import br.gov.serpro.rtc.domain.service.exception.ErroInternoSistemaException;
import br.gov.serpro.rtc.domain.service.exception.ErroXmlException;
import br.gov.serpro.rtc.domain.service.exception.FormaAplicacaoNaoDefinidaException;
import br.gov.serpro.rtc.domain.service.exception.ImpostoSeletivoInformadoIndevidamenteException;
import br.gov.serpro.rtc.domain.service.exception.ImpostoSeletivoNaoInformadoException;
import br.gov.serpro.rtc.domain.service.exception.IncompatibilidadeSuspensaoException;
import br.gov.serpro.rtc.domain.service.exception.ItemDuplicadoException;
import br.gov.serpro.rtc.domain.service.exception.MunicipioNaoEncontradoException;
import br.gov.serpro.rtc.domain.service.exception.MunicipioNaoPertencenteException;
import br.gov.serpro.rtc.domain.service.exception.NbsCompletoNaoInformadoException;
import br.gov.serpro.rtc.domain.service.exception.NbsNaoEncontradaException;
import br.gov.serpro.rtc.domain.service.exception.NbsNaoVinculadaException;
import br.gov.serpro.rtc.domain.service.exception.NcmCompletoNaoInformadoException;
import br.gov.serpro.rtc.domain.service.exception.NcmNaoEncontradaException;
import br.gov.serpro.rtc.domain.service.exception.NcmNaoVinculadaException;
import br.gov.serpro.rtc.domain.service.exception.NcmNbsSimultaneasException;
import br.gov.serpro.rtc.domain.service.exception.NegocioException;
import br.gov.serpro.rtc.domain.service.exception.NomenclaturaException;
import br.gov.serpro.rtc.domain.service.exception.SiglaDFeNaoEncontradaException;
import br.gov.serpro.rtc.domain.service.exception.SituacaoTributariaNaoEncontradaException;
import br.gov.serpro.rtc.domain.service.exception.TributacaoRegularInformadaIndevidamenteException;
import br.gov.serpro.rtc.domain.service.exception.TributacaoRegularNaoInformadaException;
import br.gov.serpro.rtc.domain.service.exception.UfNaoEncontradaException;
import lombok.Getter;
import lombok.NonNull;

@Getter
public enum ProblemType {

    CAMPO_INVALIDO(CampoInvalidoException.class,
            "Campo inválido", "campo-invalido"),

    ALIQUOTA_REFERENCIA_NAO_ENCONTRADA(AliquotaReferenciaNaoEncontradaException.class,
            "Alíquota de referência não encontrada", "aliquota-referencia-nao-encontrada"),

    ALIQUOTA_AD_REM_NAO_ENCONTRADA(AliquotaAdRemNaoEncontradaException.class,
            "Alíquota ad rem não encontrada", "aliquota-ad-rem-nao-encontrada"),

    CLASSIFICACAO_TRIBUTARIA_NAO_ENCONTRADA(ClassificacaoTributariaNaoEncontradaException.class,
            "Classificação tributária não encontrada", "classificacao-tributaria-nao-encontrada"),
    CLASSIFICACAO_TRIBUTARIA_NAO_VINCULADA_SITUACAO_TRIBUTARIA(
            ClassificacaoTributariaNaoVinculadaSituacaoTributariaException.class,
            "Classificação tributária não vinculada à situação tributária",
            "classificacao-tributaria-nao-vinculada-situacao-tributaria"),

    INCOMPATIBILIDADE_SUSPENSAO(IncompatibilidadeSuspensaoException.class,
            "Incompatibilidade com suspensão", "incompatibilidade-suspensao"),

    GRUPO_TRIBUTACAO_REGULAR_NAO_INFORMADO(TributacaoRegularNaoInformadaException.class,
            "Grupo de tributação regular não informado", "grupo-tributacao-regular-nao-informado"),

    GRUPO_TRIBUTACAO_REGULAR_INFORMADO_INDEVIDAMENTE(TributacaoRegularInformadaIndevidamenteException.class,
            "Grupo de tributação regular informado indevidamente", "grupo-tributacao-regular-informado-indevidamente"),

    IMPOSTO_SELETIVO_NAO_INFORMADO(ImpostoSeletivoNaoInformadoException.class,
            "Dados do Imposto Seletivo não informados", "dados-imposto-seletivo-nao-informados"),

    IMPOSTO_SELETIVO_INFORMADO_INDEVIDAMENTE(ImpostoSeletivoInformadoIndevidamenteException.class,
            "Dados do Imposto Seletivo informados indevidamente", "dados-imposto-seletivo-informados-indevidamente"),

    ERRO_NOMENCLATURA(NomenclaturaException.class, "Erro de nomenclatura", "erro-nomenclatura"),

    ERRO_INTERNO_SISTEMA(ErroInternoSistemaException.class, "Erro interno do sistema", "erro-interno-de-sistema"),

    FORMA_APLICACAO_NAO_DEFINIDA(FormaAplicacaoNaoDefinidaException.class,
            "Forma de Aplicação de Percentual não definida", "forma-aplicacao-percentual-nao-definida"),

    NCM_E_NBS_SIMULTANEAS(NcmNbsSimultaneasException.class, "NCM e NBS informadas simultaneamente",
            "ncm-nbs-simultaneas"),

    NCM_COMPLETO_NAO_INFORMADO(NcmCompletoNaoInformadoException.class, "NCM completo não informado",
            "ncm-completo-nao-informado"),

    NBS_COMPLETO_NAO_INFORMADO(NbsCompletoNaoInformadoException.class, "NBS completo não informado",
            "nbs-completo-nao-informado"),

    NCM_NAO_ENCONTRADO(NcmNaoEncontradaException.class, "NCM não encontrada", "ncm-nao-encontrada"),

    NCM_NAO_VINCULADA(NcmNaoVinculadaException.class, "NCM não vinculada", "ncm-nao-vinculada"),

    NBS_NAO_VINCULADA(NbsNaoVinculadaException.class, "NBS não vinculada", "nbs-nao-vinculada"),

    NBS_NAO_ENCONTRADO(NbsNaoEncontradaException.class, "NBS não encontrada", "nbs-nao-encontrada"),

    ITEM_DUPLICADO(ItemDuplicadoException.class, "Item duplicado", "item-duplicado"),

    MUNICIPIO_NAO_ENCONTRADO(MunicipioNaoEncontradoException.class, "Município não encontrado",
            "municipio-nao-encontrado"),
    MUNICIPIO_NAO_PERTENCE_UF(MunicipioNaoPertencenteException.class, "Município não pertencente à UF",
            "municipio-nao-pertencente-uf"),

    SITUACAO_TRIBUTARIA_NAO_ENCONTRADA(SituacaoTributariaNaoEncontradaException.class,
            "Situação tributária não encontrada", "situacao-tributaria-nao-encontrada"),

    UF_NAO_ENCONTRADA(UfNaoEncontradaException.class, "UF não encontrada", "uf-nao-encontrada"),

    ERRO_XML(ErroXmlException.class, "Erro de validação de XML", "erro-xml"),

    ERRO_FALTA_IMPLEMENTACAO(ErroFaltaImplementacaoException.class, "Classificação tributária em desenvolvimento", "erro-falta-implementacao"),

    ERRO_NEGOCIO(NegocioException.class, "Violação de regra de negócio", "erro-negocio"),
    
    ERRO_CAPTCHA(CaptchaException.class, "Erro de Captcha", "erro-captcha"),

    METHOD_ARGUMENT_TYPE_MISMATCH(MethodArgumentTypeMismatchException.class, "Tipo de argumento inválido", "tipo-argumento-invalido"),
    
    ERRO_SISTEMA(Exception.class, "Erro de sistema não previsto", "erro-de-sistema-nao-previsto"),
    
    SIGLA_DFE_NAO_ENCONTRADA(SiglaDFeNaoEncontradaException.class, "Sigla DFe não reconhecida", "sigla-dfe-nao-reconhecida");
    
    private final Class<? extends Exception> classeErro;
    private final String titulo;
    private final String path;

    private ProblemType(Class<? extends Exception> classeErro, String titulo, String path) {
        this.classeErro = classeErro;
        this.titulo = titulo;
        this.path = path;
    }

    public URI getURI() {
        return URI.create(String.format("%s/errors/%s", HttpUtils.getBaseURL(), path));
    }

    public static <E extends Exception> ProblemType from(@NonNull final E e) {
        Class<? extends Exception> classeErro = e.getClass();
        return Stream.of(values()).filter(t -> t.getClasseErro().equals(classeErro)).findFirst().orElse(ERRO_SISTEMA);
    }

}
