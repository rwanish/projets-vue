<template>
  <div class="card">
    <h2>Enregistrer une participationnnnnnnnn</h2>

    <label for="personne">Personne</label>
    <select v-model="form.personne" id="personne">
      <option v-for="person in personnes" :key="person.matricule" :value="person.matricule">
        {{ person.nom }} {{ person.prenom }}
      </option>
    </select>

    <label for="projet">Projet</label>
    <select v-model="form.projet" id="projet">
      <option v-for="projet in projets" :key="projet.id" :value="projet.id">
        {{ projet.nom }}
      </option>
    </select>

    <label for="role">Rôle</label>
    <input v-model="form.role" id="role" type="text" placeholder="Développeur" />

    <label for="pourcentage">Pourcentage</label>
    <input type="range" v-model="form.pourcentage" min="0" max="100" step="10" />
    <p>{{ form.pourcentage }}%</p>

    <button @click="enregistrer">Enregistrer</button>

    <div v-if="error" class="error">
          {{ error }}
    </div>

  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        personne: null,
        projet: null,
        role: '',
        pourcentage: 0 ,
      },
      personnes: [],
      projets: [],
      error: null,
    };
  },
  methods: {
     async fetchData() {
       try {
         const personnesResponse = await fetch('http://localhost:8989/api/personnes');
         this.personnes = await personnesResponse.json();

         const projetsResponse = await fetch('http://localhost:8989/api/projets');
         this.projets = await projetsResponse.json();
       } catch (error) {
         console.error('Erreur lors du chargement des données :', error);
         this.error = 'Erreur lors du chargement des données. Veuillez réessayer.';
       }
     },
     async enregistrer() {
       try {
         const response = await fetch('http://localhost:8989/api/gestion/participation', {
           method: 'POST',
           headers: { 'Content-Type': 'application/json' },
           body: JSON.stringify({
             matricule: this.form.personne,
             codeProjet: this.form.projet,
             role: this.form.role,
             pourcentage: this.form.pourcentage / 100,
           }),
         });

         if (!response.ok) {
           const errorData = await response.json();
           throw new Error(errorData.message || 'Échec de l’enregistrement');
         }

         alert('Participation enregistrée avec succès !');
         this.resetForm();
       } catch (error) {
         console.error('Erreur lors de l’enregistrement :', error);
         this.error = error.message;
       }
     },
     resetForm() {
       this.form = {
         personne: null,
         projet: null,
         role: '',
         pourcentage: 0,
       };
       this.error = null;
     },
   },
   mounted() {
     this.fetchData();
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
