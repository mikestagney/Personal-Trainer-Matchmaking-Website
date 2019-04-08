<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Trainer Profile</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        There were problems registering this trainer.
      </div>
      <label for="city" class="sr-only">City</label>
      <input 
        type="text"
        id="city"
        class="form-control"
        placeholder="city"
        v-model="trainer.city"/>
      <label for="state" class="sr-only">State</label>
      <select v-model="trainer.state">
        <option disabled value="">State</option>
        <option value="AK">Alaska</option>
        <option value="AL">Alabama</option>
        <option value="AR">Arkansas</option>
        <option value="AZ">Arizona</option>
        <option value="CA">California</option>
        <option value="CO">Colorado</option>
        <option value="CT">Connecticut</option>
        <option value="DC">District of Columbia</option>
        <option value="DE">Delaware</option>
        <option value="FL">Florida</option>
        <option value="GA">Georgia</option>
        <option value="HI">Hawaii</option>
        <option value="IA">Iowa</option>
        <option value="ID">Idaho</option>
        <option value="IL">Illinois</option>
        <option value="IN">Indiana</option>
        <option value="KS">Kansas</option>
        <option value="KY">Kentucky</option>
        <option value="LA">Louisiana</option>
        <option value="MA">Massachusetts</option>
        <option value="MD">Maryland</option>
        <option value="ME">Maine</option>
        <option value="MI">Michigan</option>
        <option value="MN">Minnesota</option>
        <option value="MO">Missouri</option>
        <option value="MS">Mississippi</option>
        <option value="MT">Montana</option>
        <option value="NC">North Carolina</option>
        <option value="ND">North Dakota</option>
        <option value="NE">Nebraska</option>
        <option value="NH">New Hampshire</option>
        <option value="NJ">New Jersey</option>
        <option value="NM">New Mexico</option>
        <option value="NV">Nevada</option>
        <option value="NY">New York</option>
        <option value="OH">Ohio</option>
        <option value="OK">Oklahoma</option>
        <option value="OR">Oregon</option>
        <option value="PA">Pennsylvania</option>
        <option value="PR">Puerto Rico</option>
        <option value="RI">Rhode Island</option>
        <option value="SC">South Carolina</option>
        <option value="SD">South Dakota</option>
        <option value="TN">Tennessee</option>
        <option value="TX">Texas</option>
        <option value="UT">Utah</option>
        <option value="VA">Virginia</option>
        <option value="VT">Vermont</option>
        <option value="WA">Washington</option>
        <option value="WI">Wisconsin</option>
        <option value="WV">West Virginia</option>
        <option value="WY">Wyoming</option>
      <label for="price" class="sr-only">Price Per hour</label>
      <input
        type="text"
        id="price"
        class="form-control"
        placeholder="Enter your hourly rate"
        v-model="trainer.price"
        required
        autofocus
      />
      <label for="background" class="sr-only">Tell us about yourself</label>
      <input
        type="text"
        id="background"
        class="form-control"
        placeholder="Background"
        v-model="trainer.background"
        required
        autofocus
      />
      <label for="philosophy" class="sr-only">Your training Philosophy</label>
      <input
        type="text"
        id="philosophy"
        class="form-control"
        placeholder="Philosophy"
        v-model="trainer.philosophy"
        required
        autofocus
      />
      <label for="certifications" class="sr-only">Certifications</label>
      <input 
        type="text"
        id="certifications"
        class="form-control"
        placeholder="Certifications"
        v-model="trainer.certifications"/>
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create profile
      </button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'updatetrainerprofile',
  props: {
    apiUrl: String,
    trainerId: Number
  },
  data() {
    return {
      user: {
        city: '',
        state: '',
        background: '',
        philosophy: '',
        certifications: '',
      },
      registrationErrors: false,
    };
  },
  methods: {
    saveProfile() {
      this.trainerId === 0 ? this.createProfile() : this.updateProfile();
    },
    createProfile() {
      fetch(this.apiUrl, {
        method: 'POST',
        headers: {
          "Content-Type": "application/json"
        },
      })
      . then ((response) => {
        if(response.ok) {
          this.$emit('showProfile');
        }
      })
      .catch((err) => console.error(err));
    },
    updateProfile() {
      fetch(`${this.apiUrl}/${this.trainerId}`, {
        method: 'PUT',
        headers: {
          "Content-Type": "application/json"
        },
      })
      .then((response) => {
        if(response.ok) {
          this.$emit('showProfile');
        }
      })
      .catch((err) => console.error(err));
    }
  },
};
</script>

<style>
html,
body {
  height: 100%;
}
#app {
  height: 100%;
}
#register {
  height: 100%;
  display: -ms-flexbox;
  display: flex;
  -ms-flex-align: center;
  align-items: center;
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #f5f5f5;
  font-family: 'Roboto Condensed', sans-serif;
}

form {
  text-align: center;
}

.form-register {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: auto;
}

.form-register .form-control {
  position: relative;
  box-sizing: border-box;
  height: auto;
  padding: 10px;
  font-size: 16px;
}
.form-register .form-control:focus {
  z-index: 2;
}
.form-register input[type='text'] {
  margin-bottom: 10px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-register input[type='password'] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>
