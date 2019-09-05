<template>
  <div>
    <div class="console" v-if="console_on">
      <p v-text="console_text"></p>
      <div class="options">
        <button class="button" v-for="opcao in console_options" @click="fazerAcao(opcao)">{{opcao}}</button>
      </div>
      <a class="button" @click="console_on = false">
        <i class="material-icons">exit_to_app</i>
      </a>
    </div>
    <img
      class="fixed-img-console"
      v-if="console_on"
      src="https://i.pinimg.com/originals/67/e7/b1/67e7b10e05ed23b3ab70ba6eeb67f449.png"
    >

    <nav class="navbar navbar-expand-lg navbar-light bg-light d-flex mb-3">
      <div class="collapse navbar-collapse" id="conteudoNavbarSuportado">
        <ul class="navbar-nav mr-auto text-center">
          <h6>Sistema Especialista - IA 2019</h6>
        </ul>
      </div>
    </nav>

    <modal name="responsavel" class="row"></modal>

    <div class v-bind:class="{ blurr: console_on }">
      <div class="row p-3">
        <div class="col-8">
          <div class="card-deck">
            <div class="card">
              <div class="card-header d-flex justify-content-center">
                <h5>Responsáveis</h5>
              </div>
              <div class="card-body">
                <div class="flex-center">
                  <ul class="w-100 mb-1 mt-1">
                    <li v-for="responsavel in responsaveis" class="mt-2 mb-2">
                      <span>
                        <i class="material-icons">person</i>
                        {{responsavel.nome}}
                      </span>
                      <button class="button is-small" @click="deleteResponsavel(responsavel.id)">
                        <i class="material-icons">delete</i>
                      </button>
                    </li>
                  </ul>
                </div>
                <b-field>
                  <b-input placeholder="nome do responsável" v-model="nomeResponsavel"></b-input>
                </b-field>
              </div>
              <div class="card-footer">
                <button class="button is-medium is-dark" @click="addResponsavel">
                  <span>
                    <i class="material-icons">person</i> &nbsp; Adicionar Responsável
                  </span>
                </button>
              </div>
            </div>
            <div class="card">
              <div class="card-header d-flex justify-content-center">
                <h5>Projetos</h5>
              </div>
              <div class="card-body">
                <div class="flex-center">
                  <ul class="w-100 mt-1 mb-1">
                    <li v-for="projeto in projetos">
                      <a @click="irParaProjeto(projeto)">
                        <i class="material-icons">assignment</i>
                        {{projeto.nomeProjeto}}
                      </a>
                      <button class="button is-small" @click="deleteProjeto(projeto.id)">
                        <i class="material-icons">delete</i>
                      </button>
                    </li>
                  </ul>
                </div>
                <b-field>
                  <b-input placeholder="nome do projeto" v-model="formNomeProjeto"></b-input>
                </b-field>
                <b-select placeholder="Selecione um responsável" v-model="formNomeResponsavel">
                  <option
                    v-for="option in responsaveis"
                    :value="option.nome"
                    :key="option.id"
                  >{{ option.nome }}</option>
                </b-select>
              </div>
              <div class="card-footer">
                <button class="button is-medium is-dark" @click="addProjeto">
                  <span>
                    <i class="material-icons">assignment</i> &nbsp; Adicionar Projeto
                  </span>
                </button>
              </div>
            </div>
          </div>
          <div class="col-6 pl-0">
            <div class="card mt-3">
              <div class="card-header d-flex justify-content-center">
                <h5>Componentes</h5>
              </div>
              <div class="card-body">
                <ul class="w-100 mb-1 mt-1">
                  <li class="mt-2 mb-2">
                    <span>
                      <i class="material-icons">person</i>Victor Emiliano Silva Flores
                    </span>
                  </li>
                  <li class="mt-2 mb-2">
                    <span>
                      <i class="material-icons">person</i>Raphael Braz Giudice dos Santos
                    </span>
                  </li>
                  <li class="mt-2 mb-2">
                    <span>
                      <i class="material-icons">person</i>Pedro Cotta Mendes
                    </span>
                  </li>
                  <li class="mt-2 mb-2">
                    <span>
                      <i class="material-icons">person</i>Grabriel Martins Villas
                    </span>
                  </li>
                  <li class="mt-2 mb-2">
                    <span>
                      <i class="material-icons">person</i>Ana Luiza dos Santos Campos
                    </span>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
        <div class="col-4">
          <div class="card-deck">
            <div class="card">
              <div class="card-body row">
                <div class="col-12 justify-content-center">
                  <b-message
                    title="Info"
                    type="is-info"
                    class="text-center"
                  >Clique na Akame quando estiver pronto...</b-message>
                  <img
                    @click="iniciarMotor"
                    v-if="!console_on"
                    style="cursor:pointer; height: 450px;"
                    src="https://vignette.wikia.nocookie.net/liberproeliis/images/4/4b/Akame_2.png/revision/latest?cb=20161211101646&path-prefix=pt-br"
                  >
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <modal name="hello-world">
      <div class="container" v-if="projetoAtivo">
        <div class="project-header">
          <h3>Projeto: {{projetoAtivo.nomeProjeto}}</h3>
          <span>Responsável: {{projetoAtivo.responsavelProjeto}}</span>
        </div>

        <div class="row">
          <div class="col-12">
            <div class="row" style="min-height: 325px">
              <div class="col-6">
                <div class="card">
                  <b-collapse class="panel" :open.sync="isObjetivosOpen">
                    <div slot="trigger" class="panel-heading">
                      <strong>Objetivos</strong>
                    </div>
                    <div class="panel-block">
                      <objetivos1 :projeto="projetoAtivo.nomeProjeto"/>
                    </div>
                  </b-collapse>
                </div>
              </div>
              <div class="col-6">
                <div class="card">
                  <b-collapse class="panel" :open.sync="isFatosOpen">
                    <div slot="trigger" class="panel-heading">
                      <strong>Fatos</strong>
                    </div>
                    <div class="panel-block">
                      <fatos1 :projeto="projetoAtivo.nomeProjeto"/>
                    </div>
                  </b-collapse>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-12">
                <div class="card mt-3">
                  <b-collapse class="panel" :open.sync="isRegrasOpen">
                    <div slot="trigger" class="panel-heading">
                      <strong>Regras</strong>
                    </div>
                    <div class="panel-block">
                      <regras1 :projeto="projetoAtivo.nomeProjeto"/>
                    </div>
                  </b-collapse>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </modal>

    <b-loading :active.sync="isLoading" :canCancel="true"></b-loading>
  </div>
</template>

<script>
import regras1 from "./regras";
import objetivos1 from "./objetivos";
import fatos1 from "./fatos";

export default {
  components: {
    objetivos1,
    fatos1,
    regras1
  },
  name: "SistemasEspecialistas",
  data() {
    return {
      console_on: false,
      console_options: [],
      console_text: "",
      isObjetivosOpen: false,
      isFatosOpen: false,
      isRegrasOpen: true,
      isLoading: true,
      formNomeProjeto: "",
      formNomeResponsavel: "",
      nomeResponsavel: "",
      responsaveis: [],
      projetos: [],
      projetoAtivo: null,
      regras: [],
      fatos: [],
      historicoPerguntaResposta: [],
      perguntas: [],
      respostas: [],
      regrasProjeto: [],
      contador: 0,
      projectName: "adfasdsfa",
      valoresCorretos: [],
      respostasDoidera: []
    };
  },
  created() {
    const vm = this;
    vm.isLoading = true;
    setTimeout(() => {
      vm.isLoading = false;
    }, 2000);
  },
  firestore() {
    return {
      responsaveis: this.$db.collection("responsaveis"),
      projetos: this.$db.collection("projetos"),
      fatos: this.$db.collection("fatos"),
      regras: this.$db.collection("regras")
    };
  },
  methods: {
    getRespostaByPergunta(pergunta) {
      this.respostas = [];
      this.fatos.map(j => {
        if (pergunta === j.pergunta) {
          this.respostas = j.valores;
        }
      });
    },
    respostaDaPerguntaForUmProjeto(resposta) {
      const ProjetosMap = this.projetos.map(e => {
        return e.nomeProjeto;
      });
      return ProjetosMap.indexOf(resposta) > -1;
    },
    respostaDaPerguntaForUmaOpcao(resposta) {
      return this.respostas.indexOf(resposta) > -1;
    },
    fazerAcao(resposta) {
      this.historicoPerguntaResposta.push({
        pergunta: this.console_text,
        resposta: resposta
      });
      if (resposta === "Claro!") {
        this.console_text = "Por favor, selecione um projeto:";
        this.console_options = this.projetos.map(e => {
          return e.nomeProjeto;
        });
      } else if (resposta === "Agora não, obg...") {
        this.console_text = "Ok...  =(  tchau...";

        setTimeout(() => {
          this.console_on = false;
        }, 1000);
      } else if (this.respostaDaPerguntaForUmProjeto(resposta)) {
        this.regraProjeto = [];
        //PEGA A REGRA PARA ATINGIR O OBJETIVO
        this.regras.map(regra => {
          if (regra.projeto == resposta) {
            this.regraProjeto.push(regra);
          }
        });
        //PEGA AS PERGUNTAS RELACIONADAS A ESSA REGRA
        for (let z = 0; z < this.regraProjeto.length; z++) {
          this.regraProjeto[z].condicoes.forEach(condicao => {
            if (this.perguntas.length == 0) {
              this.perguntas.push(condicao.pergunta);
            } else {
              if (this.perguntas.indexOf(condicao.pergunta) == -1) {
                this.perguntas.push(condicao.pergunta);
              }
            }
          });
        }
        //PEGA AS RESPOSTAS DESSA PERGUNTA
        this.getRespostaByPergunta(this.perguntas[this.contador]);
        //EXIBE NO CONSOLE DA MADOKA
        this.console_text = this.perguntas[this.contador];
        this.console_options = this.respostas;
        //SOBE O CONTADOR PARA IR PRA PROXIMA PERGUNTA
        this.contador++;
      } else if (
        this.respostaDaPerguntaForUmaOpcao(resposta) &&
        this.contador < this.perguntas.length
      ) {
        this.console_text = this.perguntas[this.contador];
        this.console_options = this.respostas;
        this.contador++;
      } else {
        for (let c = 0; c < this.regraProjeto.length; c++) {
          this.regraProjeto[c].condicoes.forEach(e => {
            this.valoresCorretos.push({
              pergunta: e.pergunta,
              resposta: e.valor,
              objetivo: this.regraProjeto[c].objetivo,
              tipo: e.tipo
            });
          });
        }

        let respostasDoidera = this.historicoPerguntaResposta.splice(0, 2);
        respostasDoidera = this.historicoPerguntaResposta.map(e => {
          return { pergunta: e.pergunta, resposta: e.resposta };
        });

        let objetivo = "";
        let arr = "";
        let monta_condicao = [];
        for (var h = 0; h < this.valoresCorretos.length; h++) {
          if (h == 0) {
            objetivo = this.valoresCorretos[h].objetivo;
          }
          let objetivo_h = this.valoresCorretos[h].objetivo;
          if (objetivo != objetivo_h) {
            monta_condicao.push({ objetivo: objetivo, condicoes: arr });
            objetivo = objetivo_h;
            arr = "";
          } else {
            objetivo = this.valoresCorretos[h].objetivo;
          }
          if (this.valoresCorretos[h].tipo == "") {
            var pergunta = this.valoresCorretos[h].pergunta;
            var resposta = this.valoresCorretos[h].resposta;
            respostasDoidera.map(e => {
              var pergunta_feita = e.pergunta;
              if (pergunta_feita == pergunta) {
                if (e.resposta == resposta) {
                  arr += "true/";
                } else {
                  arr += "false/";
                }
                arr += this.valoresCorretos[h + 1].tipo + "/";
              }
            });
          } else {
            var pergunta = this.valoresCorretos[h].pergunta;
            var resposta = this.valoresCorretos[h].resposta;
            respostasDoidera.map(e => {
              var pergunta_feita = e.pergunta;
              if (pergunta_feita == pergunta) {
                if (e.resposta == resposta) {
                  arr += "true/";
                } else {
                  arr += "false/";
                }
                if (
                  h + 1 < this.valoresCorretos.length &&
                  objetivo == this.valoresCorretos[h + 1].objetivo
                ) {
                  arr += this.valoresCorretos[h + 1].tipo + "/";
                }
              }
            });
          }
          if (h == this.valoresCorretos.length - 1) {
            monta_condicao.push({ objetivo: objetivo, condicoes: arr });
          }
        }
        let resposta_arr = [];
        for (let k = 0; k < monta_condicao.length; k++) {
          let objetivo_k = monta_condicao[k].objetivo;
          let condicao = monta_condicao[k].condicoes.split("/");
          condicao.pop();
          let primeira_condicao = "";
          let segunda_condicao = "";
          for (let y = 0; y < condicao.length; y++) {
            if (y + 2 <= condicao.length) {
              if (condicao[y + 1] == "OU" && condicao.length > 3) {
                  console.log('aqui');
                  if (condicao[y + 2] == "true") {
                    primeira_condicao = true;
                  } else {
                    primeira_condicao = false;
                  }
                  if (condicao[y + 3] == "true") {
                    segunda_condicao = true;
                  } else {
                    segunda_condicao = false;
                  }
                  if (condicao[y + 3] == "OU") {
                    if (primeira_condicao || segunda_condicao) {
                      condicao[y + 2] = true;
                    } else {
                      condicao[y + 2] = false;
                    }
                  } else if (condicao[y + 3] == "E") {
                    if (primeira_condicao && segunda_condicao) {
                      condicao[y + 2] = true;
                    } else {
                      condicao[y + 2] = false;
                    }
                  }
                  y = 0;
                  console.log("2", condicao);
                  condicao = condicao.slice(4, condicao.length);
                  console.log("3", condicao);
              } else {
                if (y == 0) {
                  if (condicao[y] == "true") {
                    primeira_condicao = true;
                  } else {
                    primeira_condicao = false;
                  }
                  if (condicao[y + 2] == "true") {
                    segunda_condicao = true;
                  } else {
                    segunda_condicao = false;
                  }
                } else {
                  if (condicao[y] == "OU") {
                    if (primeira_condicao || segunda_condicao) {
                      condicao[y + 1] = true;
                    } else {
                      condicao[y + 1] = false;
                    }
                  } else if (condicao[y] == "E") {
                    if (primeira_condicao && segunda_condicao) {
                      condicao[y + 1] = true;
                    } else {
                      condicao[y + 1] = false;
                    }
                  }
                }
                if (y != 0) {
                  y = 0;           
                  console.log("3", condicao);
                  condicao = condicao.slice(2, condicao.length);
                  console.log("3", condicao);
                }
              }
            }
          }
          resposta_arr.push({ objetivo: objetivo_k, final: condicao });
        }
        console.log(resposta_arr);

        let objetivos_final = "";
        resposta_arr.map(e => {
          var aux1 = e.objetivo;
          var resultado = e.final[0];
          if (resultado == true || resultado == "true") {
            objetivos_final += aux1 + ", ";
          }
        });

        if (objetivos_final == "") {
          objetivos_final = "Você não alcançou nenhum objetivo!";
        }

        this.console_text =
          "hora do resultado! objetivos alcançados:" + objetivos_final;
        this.console_options = [];
      }
    },
    iniciarMotor() {
      this.console_on = true;
      this.console_text = "Olá, eu sou Akame. Posso fazer algumas perguntas?";
      this.console_options = ["Claro!", "Agora não, obg..."];
    },
    addResponsavel() {
      if (this.nomeResponsavel != "") {
        this.$db.collection("responsaveis").add({ nome: this.nomeResponsavel });
        this.$toast.open(`Responsável adicionado com sucesso!`);
        var url = "http://localhost:8085/responsavel";
        var data = JSON.stringify({ nome: this.nomeResponsavel });
        fetch(url, {
          method: "POST",
          headers: { "Content-Type": "application/json;charset=UTF-8" },
          body: data
        })
          .then(response => response.json())
          .then(function(response) {
           
          });
        this.nomeResponsavel = "";
        
      } else {
        this.$toast.open(`Preencha o campo corretamente!`);
      }
    },
    deleteResponsavel(id) {
      this.$db
        .collection("responsaveis")
        .doc(id)
        .delete();
      this.$toast.open(`Responsável excluído com sucesso!`);
      var url = "http://localhost:8085/responsavel/";
      var data = JSON.stringify(id);
      fetch(url, {
        method: "DELETE",
        headers: { "Content-Type": "application/json;charset=UTF-8" },
        body: data
      }).then(function() {
          
        });
    },
    irParaTelaInicial() {
      this.projetoAtivo = null;
    },
    irParaProjeto(projeto) {
      this.projetoAtivo = projeto;
      this.$modal.show("hello-world");
    },
    addProjeto() {
      if (this.formNomeProjeto != "") {
        if (this.formNomeResponsavel != "") {
          this.$db.collection("projetos").add({
            nomeProjeto: this.formNomeProjeto,
            responsavelProjeto: this.formNomeResponsavel
          });
        var url = "http://localhost:8085/projeto";
        var data = JSON.stringify({
            nome: this.formNomeProjeto,
            responsavel: this.formNomeResponsavel
          });
        fetch(url, {
          method: "POST",
          headers: { "Content-Type": "application/json;charset=UTF-8" },
          body: data
        })
          .then(response => response.json())
          .then(function(response) {
            
          });
          this.$toast.open(`Projeto adicionado com sucesso!`);
        } else {
          this.$toast.open(`informe um responsavel!`);
        }
      } else {
        this.$toast.open(`Informe o nome do projeto!`);
      }
    },
    deleteProjeto(id) {
      this.$db
        .collection("projetos")
        .doc(id)
        .delete();
      this.$toast.open(`Projeto excluído com sucesso!`);
      var url = "http://localhost:8085/projeto/";
      var data = JSON.stringify(id);
      fetch(url, {
        method: "DELETE",
        headers: { "Content-Type": "application/json;charset=UTF-8" },
        body: data
      }).then(function() {
          
        });
    }
  }
};
</script>

<style>
nav.navbar {
  background-color: #000 !important;
  color: #fff;
}

body {
  background-color: #d11e36 !important;
  height: 100vh;
}

.blurr {
  animation: blur-transition 2s;
  animation-fill-mode: forwards;
}

.fixed-img-console {
  position: fixed;
  left: 0px;
  z-index: 99;
  bottom: -40px;
  width: auto;
  max-width: 50%;
  transform: scaleX(-1);
}

@keyframes blur-transition {
  0% {
    filter: blur(0px);
  }
  100% {
    filter: blur(3px);
  }
}

.console {
  background: #000000;
  height: 100px;
  position: fixed;
  width: 100%;
  z-index: 999;
  bottom: 10px;
  opacity: 1;
  display: flex;
  flex-flow: column nowrap;
  /*background: linear-gradient(to right, #6731fa , #ffffff);*/
}

.console:before {
  content: "Akame diz:";
  position: absolute;
  color: #ff0000;
  top: -18px;
  left: -5px;
  background: #000000;
  font-weight: bold;
  border: 12px solid transparent;
  border-image: 1
    url('data:image/svg+xml, <svg xmlns="http://www.w3.org/2000/svg" width="3" height="3" fill="black"><polygon points="0,1 1,0 2,0 3,1 3,2 2,3 1,3 0,2" /></svg>');
  background-clip: padding-box;
}

.console .options {
  position: absolute;
  bottom: 0px;
  margin: 0 auto;
  width: 100%;
}

.console .options button {
  margin: 5px 10px;
}

.console > a {
  width: 40px;
  position: absolute;
  right: 5px;
  bottom: 5px;
}

h1,
h2 {
  font-weight: bold;
  font-size: 24px;
  text-decoration: underline;
  margin-bottom: 10px;
}

h3 {
  font-weight: bold;
  font-size: 18px;
  text-transform: uppercase;
  text-align: left;
  text-decoration: underline;
  margin-bottom: 10px;
}

.hmm {
  width: fit-content;
  display: flex;
  flex-flow: column nowrap;
}

.hmm button.is-dark {
  width: calc(100%) !important;
}

.panel-block > div {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-flow: column nowrap;
}

.panel-block > div .taginput.control,
.panel-block > div .field {
  width: 100%;
}

em b {
  font-size: 18px;
}

.project-header {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 10px 0px;
}

.project-header > div {
  position: absolute;
  left: 5px;
  top: 10px;
}

label {
  text-align: left;
}

.flex-center {
  display: flex;
  justify-content: center;
}

.field + .control {
  margin-bottom: 0.75rem;
}

.select,
select {
  width: 100%;
}

button.is-dark {
  width: 100%;
}

button.is-small i {
  font-size: 16px;
}

ul {
  width: fit-content;
  margin-bottom: 15px;
}

ul span,
ul li > a {
  padding-right: 5px;
}

ul li {
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-bottom: 1px solid #ccc;
  padding: 3px 0px;
}

li > span,
button > span,
li > a {
  display: flex;
  align-items: center;
}

/* MEC */
.console p {
  color: #fff;
  width: fit-content;
  font-weight: bold;
  font-family: monospace;
  overflow: hidden; /* Ensures the content is not revealed until the animation */
  border-right: 0.15em solid orange; /* The typwriter cursor */
  white-space: nowrap; /* Keeps the content on a single line */
  margin: 0 auto; /*Gives that scrolling effect as the typing happens */
  letter-spacing: 0.15em; /* Adjust as needed */
  animation: typing 3.5s steps(30, end), blink-caret 0.3s step-end infinite;
}

/* The typing effect */
@keyframes typing {
  from {
    width: 0;
  }
  to {
    width: 100%;
  }
}

/* The typewriter cursor effect */
@keyframes blink-caret {
  from,
  to {
    border-color: transparent;
  }
  50% {
    border-color: orange;
  }
}

.collapse:not(.show) {
  display: block !important;
}

.v--modal {
  width: 1000px !important;
  height: 725px !important;
  top: 20px !important;
  left: 225px !important;
  overflow-y: scroll !important;
}

.message.is-info .message-header {
  background-color: #000000 !important;
  color: #fff;
}

.message.is-info .message-body {
  border-color: #000000 !important;
  color: #000000 !important;
}

.message.is-info {
  background-color: #ffffff !important;
}
</style>