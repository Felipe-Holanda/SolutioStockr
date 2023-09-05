<template>
  <div class="q-pa-md">
    <form @submit="handleRegister()">
      <q-input v-model="formRegister.name" filled type="text" placeholder="Insira seu nome" />
      <q-input v-model="formRegister.login" filled type="text" placeholder="Insira seu Usuario" />
      <q-input v-model="formRegister.password" filled type="password" placeholder="Cadastre sua senha"/>
      <q-input v-model="formRegister.registration" filled type="text" placeholder="Insira o seu CPF"/>
      <q-btn type="submit">Cadastrar</q-btn>
      <p>Já possui conta? <RouterLink></RouterLink></p>
    </form>
  </div>
</template>

<script>
import instance from '../services/axios.js'

export default {
  name: 'RegisterView',
  data () {
    return {
      formRegister: {
        name: '',
        login: '',
        password: '',
        registration: '',
        role: 'ADMIN'
      }
    }
  },
  methods: {
    handleRegister(){
      const body = this.formRegister;
      instance.post('/api/auth/register', body).then(res => {
        console.log(res)
      })
    }
  }
}
</script>
