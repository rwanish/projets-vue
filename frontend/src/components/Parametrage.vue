<template>
  <div class="card">
    <h2>Enregistrer une participation</h2>

    <label for="personne">Personne</label>
    <select v-model="form.personne" id="personne">
      <option v-for="person in personnes" :key="person" :value="person">
        {{ person }}
      </option>
    </select>

    <label for="projet">Projet</label>
    <select v-model="form.projet" id="projet">
      <option v-for="projet in projets" :key="projet.id" :value="projet.nom">
        {{ projet.nom }}
      </option>
    </select>

    <label for="role">Rôle</label>
    <input v-model="form.role" id="role" type="text" placeholder="Développeur" />

    <label for="pourcentage">Pourcentage</label>
    <input type="range" v-model="form.pourcentage" min="0" max="100" step="10" />
    <p>{{ form.pourcentage }}%</p>

    <button @click="enregistrer">Enregistrer</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        personne: '',
        projet: '',
        role: '',
        pourcentage: ,
      },
      personnes: ['', 'Jane Doe'],
      projets: [{ id: , nom: '' }, { id: , nom: '' }],
    };
  },
  methods: {
    enregistrer() {
      console.log('Participation enregistrée:', this.form);
    },
  },
};
</script>

<style scoped>
.card {
  background: white;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
  width: 300px;
}
label {
  display: block;
  margin-top: 10px;
  font-weight: bold;
}
input, select {
  width: 100%;
  padding: 8px;
  margin-top: 5px;
  border: 1px solid #ddd;
  border-radius: 5px;
}
button {
  background: blue;
  color: white;
  padding: 10px;
  border: none;
  width: 100%;
  margin-top: 15px;
  border-radius: 5px;
  cursor: pointer;
}
</style>
