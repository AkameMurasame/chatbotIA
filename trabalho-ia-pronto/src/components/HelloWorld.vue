<template>
  <div class="hello">
  <div>
    <article v-for="(personagem, idx) in personagens" :key="idx">
      <!--<img :src="personagem.foto" style="width: 200px; height: auto;">-->
      <h1>{{ personagem.nome }}</h1>
      <p>Identificação: {{personagem.id}}</p>
      <p>Rank: {{ personagem.rank }}</p>
      <p>Poder: {{ personagem.poder }}</p>
      <p>Info Adicional: {{ personagem.info }}</p>
      <p>aasdf: {{personagem.missoes}} </p>
      <!--<ul>
        <li>missões:</li>
        <li v-for="missao in personagem.missoes">{{missao.nome}}</li>
      </ul>-->
      <button @click="deletePersonagem(personagem.id)">Delete</button>
    </article>

    <div>
      <!--<b-field label="Name">
        <b-input name="asdf" placeholder="nome"></b-input>
      </b-field>-->
      <input v-model="nome" placeholder="nome">
      <input v-model="rank" placeholder="rank">
      <input v-model="poder" placeholder="poder">
      <input v-model="info" placeholder="info">
      <input v-model="foto" placeholder="URL da foto">
      <button @click="addPersonagem">Add Personagem</button>
    </div>
  </div>
    <!--<h1>msg{</h1>
    <h2>Essential Links</h2>
    <ul>
      <li>
        <a
          href="https://vuejs.org"
          target="_blank"
        >
          Core Docs
        </a>
      </li>
      <li>
        <a
          href="https://forum.vuejs.org"
          target="_blank"
        >
          Forum
        </a>
      </li>
      <li>
        <a
          href="https://chat.vuejs.org"
          target="_blank"
        >
          Community Chat
        </a>
      </li>
      <li>
        <a
          href="https://twitter.com/vuejs"
          target="_blank"
        >
          Twitter
        </a>
      </li>
      <br>
      <li>
        <a
          href="http://vuejs-templates.github.io/webpack/"
          target="_blank"
        >
          Docs for This Template
        </a>
      </li>
    </ul>
    <h2>Ecosystem</h2>
    <ul>
      <li>
        <a
          href="http://router.vuejs.org/"
          target="_blank"
        >
          vue-router
        </a>
      </li>
      <li>
        <a
          href="http://vuex.vuejs.org/"
          target="_blank"
        >
          vuex
        </a>
      </li>
      <li>
        <a
          href="http://vue-loader.vuejs.org/"
          target="_blank"
        >
          vue-loader
        </a>
      </li>
      <li>
        <a
          href="https://github.com/vuejs/awesome-vue"
          target="_blank"
        >
          awesome-vue
        </a>
      </li>
    </ul> -->
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      //msg: 'Welcome to Your Vue.js App'
      personagens: [],
      nome: '',      // <-- here
      foto: '',      // <-- here
      poder: '',
      rank: '',
      info: ''
    }
  },
  firestore () {
    return {
      //locations: db.collection('personagens').orderBy('rank')

      personagens: this.$db.collection('personagens')

    }
  },
  methods: {
    addPersonagem() {
      this.$db.collection('personagens').add({ nome: this.nome, foto: this.foto, rank: this.rank, poder: this.poder, info: this.info })
    },
    deletePersonagem (id) {
      console.log(this.$db.collection('personagens'));
      //console.log(this.$db.collection('personagens').doc(id).collection());
      //pzhQnYbDkzBZS1SudHzK
      //this.$db.collection('personagens').doc(id).delete()
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
