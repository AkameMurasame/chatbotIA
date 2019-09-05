<template>
  <div>
    <ul>
      <li v-for="regra in regras" v-if="regra.projeto === projeto">
        <em><i class="material-icons">gavel</i> <b> SE</b> <span v-for="condicao in regra.condicoes"><b> {{condicao.tipo}} </b> {{condicao.fato}} <b>===</b> {{condicao.valor}}</span> <b>ENTÃO</b> {{regra.objetivo}}</em>
      </li>
    </ul>
    <b-field label="Objetivo">
        <b-select placeholder="Selecione um objetivo" v-model="objetivo">
            <option
                v-for="option in objetivos" v-if="option.projeto === projeto"
                :value="option.nome"
                :key="option.id">
                {{ option.nome }}
            </option>
        </b-select>
    </b-field>

    <b-field label="Nome do Fato">
        <b-select placeholder="Selecione um fato" v-model="fatoSelecionado">
            <option
                v-for="option in fatos" v-if="option.projeto === projeto"
                :value="option"
                :key="option.id">
                <span >{{ option.nome }}</span>
            </option>
        </b-select>
    </b-field>

    <b-field label="Valor">
        <b-select placeholder="Selecione um valor" v-model="valorSelecionado">
            <option
                v-for="option in fatoSelecionado.valores"
                :value="option"
                :key="option">
                {{ option }}
            </option>
        </b-select>
    </b-field>

    <!--<condicoesDoidera  v-for="(condicao, index) in condicoesAdds"/>-->

    <div class="columns" style="border-top:1px solid #ccc; padding: 15px; overflow: auto;">
      <div class="column" style="position:relative;" v-for="(condicao, index) in formCondicoesAdds.condicoesAdds">
        <a @click="removeCondicao(index)" style="position:absolute; top:3px; right:3px;"><i class="material-icons">close</i></a>
        <b-field label="E / OU">
        <b-select v-model="formCondicoesAdds.condicoesAdds[index].tipo">
            <option value="E">E</option>
            <option value="OU">OU</option>
        </b-select>
        </b-field>
        <b-field label="Nome do Fato">
            <b-select :id="'fato'+index" v-model="formCondicoesAdds.condicoesAdds[index].fato" placeholder="Selecione um fato">
                <option
                    v-for="option in fatos" v-if="option.projeto === projeto"
                    :value="option"
                    :key="option.id">
                    <span >{{ option.nome }}</span>
                </option>
            </b-select>
        </b-field>
{{ formCondicoesAdds.condicoesAdds[index].fato}}
        <b-field label="Valor">
            <b-select :id="'valor'+index" v-model="formCondicoesAdds.condicoesAdds[index].valor" placeholder="Selecione um valor">
                <option
                  v-for="option in formCondicoesAdds.condicoesAdds[index].fato.valores"
                  :value="option">x
                  <span >{{ option }}</span>
              </option>
            </b-select>
        </b-field>
      </div>
    </div>

    <button class="button is-medium is-dark" style="margin-bottom:15px;" @click="addCondicao">
      <span><i class="material-icons">add</i> &nbsp; Adicionar Condição</span>
    </button>
    <button class="button is-medium is-dark" @click="addRegra">
      <span><i class="material-icons">gavel</i> &nbsp; Adicionar Regra</span>
    </button>
  </div>
</template>

<script>
export default {
  name: 'regras',
  props: ['projeto'],
  data () {
    return {
      regras: [],
      formCondicoesAdds: {
        condicoesAdds: []
      },
      condicoes: [],
      objetivo: '',
      objetivos: [],
      fatos: [],
      fatoSelecionado:[],
      valorSelecionado: ''
    }
  },
  firestore () {
    return {
      objetivos: this.$db.collection('objetivos'),
      fatos: this.$db.collection('fatos'),
      regras: this.$db.collection('regras')
    }
  },
  methods: {
    getPerguntaByFato(fato){
      let retorno;
      this.fatos.map(e=>{
        if(e.nome == fato.nome){
          console.log('entrei', e.pergunta);
          retorno = e.pergunta;
        }
      })
      return retorno;
    },
    montaCondicoes(){
      // this.formCondicoesAdds.condicoesAdds.map(e=>{
      //   return e.fato = e.fato.nome;
      // })
      this.formCondicoesAdds.condicoesAdds.map(e=>{
        e.pergunta = this.getPerguntaByFato(e.fato);
        console.log('aq carai', this.getPerguntaByFato(e.fato));
        return e.fato = e.fato.nome;
      })

      this.condicoes = [
        {fato: this.fatoSelecionado.nome, tipo: '', valor: this.valorSelecionado, pergunta: this.fatoSelecionado.pergunta},
        ]
        Array.prototype.push.apply(this.condicoes, this.formCondicoesAdds.condicoesAdds);
    },
    addCondicao(){
      this.formCondicoesAdds.condicoesAdds.push({fato: '', tipo: '', valor: ''});
    },
    removeCondicao(index){
      this.formCondicoesAdds.condicoesAdds.splice(index, 1);
    },
    addRegra(){
        //if(this.objetivo != "" && this.projeto != "" && this.condicoes != "") {
            this.montaCondicoes();
            
      var url = "http://localhost:8085/regras";
      var data = JSON.stringify({
        objetivo: this.objetivo,
        projeto: this.projeto,
        condicoes: this.condicoes
      });
      console.log(this.condicoes);
      fetch(url, {
        method: "POST",
        headers: { "Content-Type": "application/json;charset=UTF-8" },
        body: data
      })
        .then(response => response.json())
        .then(function(response) {
          
        });
            this.$toast.open(`Regra adicionada com sucesso!`)
    }
  }
}
</script>