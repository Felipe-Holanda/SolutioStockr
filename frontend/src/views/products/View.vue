<script>
import axios from 'axios';

export default {
  name: "ProductsView",
  data() {
    return {
      products: []
    }
  },
  beforeRouteEnter(to, from, next) {
    if (localStorage.getItem('token')) {
      next();
    } else {
      next({ name: 'Login' });
    }
  },
  mounted() {
    this.getProducts();
  },
  methods: {
    getProducts() { // Correção: Deve ser getProducts em vez de getStudents
      axios.get('http://localhost:8080/api/products', { headers: { Authorization: `Bearer ${localStorage.getItem('token')}` } })
        .then((response) => {
          this.products = response.data;
        }).catch((error) => {
          console.log(error);
        });
    }
  }
}
</script>

<template>
  <div>
    <section class="card">
      <div class="card-header">
        <h4>
          Products
          <RouterLink to="/products/create" class="btn btn-primary float-end">
            Add Products
          </RouterLink>
        </h4>
      </div>
      <div class="card-body">
        <table class="table table-bordered">
          <thead>
            <tr>
              <th>Nome</th> <!-- Correção: Adicione a tag <th> para cada cabeçalho -->
              <th>Preço</th>
              <th>Quantidade</th>
              <th>Fornecedor</th>
              <th>CNPJ</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            <!-- Aqui você deve iterar sobre os produtos e criar linhas da tabela -->
            <tr v-for="product in products" :key="product.id">
              <td>{{ product.nome }}</td>
              <td>{{ product.preco }}</td>
              <td>{{ product.quantidade }}</td>
              <td>{{ product.fornecedor }}</td>
              <td>{{ product.cnpj }}</td>
              <td><!-- Adicione botões de ação aqui --></td>
            </tr>
          </tbody>
        </table>
      </div>
    </section>
  </div>
</template>
