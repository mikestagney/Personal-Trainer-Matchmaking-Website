<template>
  <default-layout>
     <div class="jumbotron addTrainerHeader shadow">
            <div class="container">
        <div class="site-heading text-dark text-center pinkTransparent p-2 shadow">
            <h1 class="display-5 p-2 ">Confirmation</h1>
            <h4>Look at you go! You have been booked with {{trainer.firstName}}!</h4>
            <h4>Get chatting with your new trainer now to come up with a customized fitness plan that will meet and exceed all of your needs.</h4>
            <router-link to="/messages" exact>
                    <button class="text-light btn-lg btn-info mt-3">Message</button>
            </router-link>
    </div>
    </div>
    </div>
  </default-layout>
</template>

<script>
import auth from '../auth';
import DefaultLayout from '@/layouts/DefaultLayout';

export default {
  name: 'addTrainer',
  components: {
    DefaultLayout 
  },
  data(){
        return{
            TrainerID: this.$route.params.TrainerID,
        }
    },
  created() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/addTrainer/${this.TrainerID}`, {
      method: 'Post',
        headers: new Headers ({
          Authorization: 'Bearer ' + auth.getToken(),
        }),
        credentials: 'same-origin',
      }) 
        .then(() => {
            this.$router.push('/trainer/profile/' + this.TrainerID);
        })
        .catch((err) => console.error(err));
    }
}
</script>

<style>

.addTrainerHeader {
    background-image: url('/img/fitnessImage9.jpg');
    background-size: cover;
    background-position: center;
    border-radius: 4px;
    height: 626px;
}

</style>