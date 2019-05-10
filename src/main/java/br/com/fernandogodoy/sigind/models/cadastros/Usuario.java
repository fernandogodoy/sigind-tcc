/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fernandogodoy.sigind.models.cadastros;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;




/**
 *
 * @author Fernando
 */
@Entity
@Table(name="USUARIO")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDUSUARIO", nullable = false)
    private Long idUsuario;

    @Length(min = 5, max = 20, message = "O Usuário deve conter entre 5 e 20 caracteres")
    @Column(name="USUARIO", nullable = false)
    private String usuario;

    @Length(min = 5, max = 20, message = "O Senha deve conter entre 5 e 20 caracteres")
    @Column(name="SENHA", nullable = false)
    private String senha;

    @NotNull(message="Selecione um funcionário")
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="IDFUNCIONARIO", nullable = false, unique = true)
    private Funcionario funcionario;

    @Temporal(TemporalType.DATE)
    @Column(name="DTCADASTRO", nullable = false)
    private Date dtCadastro;

    @Column(name="ATIVO", nullable = false)
    private Boolean ativo;

    @Version
    @Column(name="VERSAO", nullable = false)
    private Long versao;

    @Column(name="EMPRESAU", length = 4, nullable = true)
    private String empresaUsuario;

    @Column(name="CLIENTEU", length = 4, nullable = true)
    private String clienteUsuario;

    @Column(name="FORNECEDORU", length = 4, nullable = true)
    private String fornecedorUsuario;

    @Column(name="FUNCIONARIOU", length = 4, nullable = true)
    private String funcionarioUsuario;

    @Column(name="PESSOAU", length = 4, nullable = true)
    private String pessoaUsuario;

    @Column(name="SETORU", length = 4, nullable = true)
    private String setorUsuario;

    @Column(name="FUNCAOU", length = 4, nullable = true)
    private String funcaoUsuario;

    @Column(name="MATERIAU", length = 4, nullable = true)
    private String materiaUsuario;

    @Column(name="PECAU", length = 4, nullable = true)
    private String pecaUsuario;

    @Column(name="MARCAU", length = 4, nullable = true)
    private String marcaUsuario;

    @Column(name="MAQUINAU", length = 4, nullable = true)
    private String maquinaUsuario;

    @Column(name="USUARIOU", length = 4, nullable = true)
    private String usuarioUsuario;

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public Long getVersao() {
        return versao;
    }

    public void setVersao(Long versao) {
        this.versao = versao;
    }

    public String getClienteUsuario() {
        return clienteUsuario;
    }

    public void setClienteUsuario(String clienteUsuario) {
        this.clienteUsuario = clienteUsuario;
    }

    public String getEmpresaUsuario() {
        return empresaUsuario;
    }

    public void setEmpresaUsuario(String empresaUsuario) {
        this.empresaUsuario = empresaUsuario;
    }

    public String getFornecedorUsuario() {
        return fornecedorUsuario;
    }

    public void setFornecedorUsuario(String fornecedorUsuario) {
        this.fornecedorUsuario = fornecedorUsuario;
    }

    public String getFuncaoUsuario() {
        return funcaoUsuario;
    }

    public void setFuncaoUsuario(String funcaoUsuario) {
        this.funcaoUsuario = funcaoUsuario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getFuncionarioUsuario() {
        return funcionarioUsuario;
    }

    public void setFuncionarioUsuario(String funcionarioUsuario) {
        this.funcionarioUsuario = funcionarioUsuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMaquinaUsuario() {
        return maquinaUsuario;
    }

    public void setMaquinaUsuario(String maquinaUsuario) {
        this.maquinaUsuario = maquinaUsuario;
    }

    public String getMarcaUsuario() {
        return marcaUsuario;
    }

    public void setMarcaUsuario(String marcaUsuario) {
        this.marcaUsuario = marcaUsuario;
    }

    public String getMateriaUsuario() {
        return materiaUsuario;
    }

    public void setMateriaUsuario(String materiaUsuario) {
        this.materiaUsuario = materiaUsuario;
    }

    public String getPecaUsuario() {
        return pecaUsuario;
    }

    public void setPecaUsuario(String pecaUsuario) {
        this.pecaUsuario = pecaUsuario;
    }

    public String getPessoaUsuario() {
        return pessoaUsuario;
    }

    public void setPessoaUsuario(String pessoaUsuario) {
        this.pessoaUsuario = pessoaUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSetorUsuario() {
        return setorUsuario;
    }

    public void setSetorUsuario(String setorUsuario) {
        this.setorUsuario = setorUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuarioUsuario() {
        return usuarioUsuario;
    }

    public void setUsuarioUsuario(String usuarioUsuario) {
        this.usuarioUsuario = usuarioUsuario;
    }
    
}
