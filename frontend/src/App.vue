

<template>
  <header>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
      <div class="container-fluid">
      <RouterLink to="/" class="navbar-brand">SolutioStockr</RouterLink>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

          <button class="btn btn-outline-success" type="submit">Logout</button>
        </div>
    </nav>
  </header>

  <RouterView />
</template>

