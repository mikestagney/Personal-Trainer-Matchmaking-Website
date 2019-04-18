<template>
    <default-layout>
        <div class="jumbotron homeHeader shadow">
            <div class="container">
                <div class="site-heading text-light orangeTransparent p-2 shadow">
                    <h1 class="display-4 p-2">Welcome to Workout</h1>
                    <h3>A Client - Trainer Matcher</h3>
                    <h2 class="lead">Find a personal trainer and create a customized fitness plan that will &ldquo;work out&rdquo; for YOU!</h2>
                </div>
                </div>
        </div>
        <div class="row">
            <div class="col subheading p-2 shadow border border-info">
                <h4 class="orangeText text-center">Testimonial</h4>
                <hr class="my-3">
                <div class="hexa">
                    <div class="hex1">
                        <div class="hex2 shadow">
                            <img src="/img/testimonial.png" alt="" width="200" height="193" />
                        </div>
                    </div>
                </div>
                <p class="mt-4"><span class="font-italic"> "Workout made it easy for me to set goals for myself and stick to them with the help of a personal trainer. The easy to access, online workout plans kept me on track at all times and if I did struggle, I was able to get in contact with my trainer right away. I'll be recommending Workout to all my friends!"</span> - John, Cincinnati</p>
            </div>
            <div class="col subheading p-2 ml-4 shadow border border-info">
                <h4 class="orangeText text-center">Benefits</h4>
                <hr class="my-3">
                 <div class="hexa">
                    <div class="hex1">
                        <div class="hex2 shadow">
                            <img src="/img/fitnessImage18.jpg" alt="" width="200" height="193" />
                        </div>
                    </div>
                </div>
                <p class="mt-4">Working out can be intimidating. It’s normal to have goals and be unsure how to achieve them, and it’s completely normal to lack motivation at times. A personal trainer can help you overcome your obstacles and take you to new heights on your fitness journey. There are trainers to suit all personalities, fitness types, and budgets, and our site makes it easy to find the perfect fit for you.</p>
            </div>
            <div class="col subheading p-2 ml-4 shadow border border-info">
                <h4 class="orangeText text-center">About</h4>
                <hr class="my-3">
                <div class="hexa">
                    <div class="hex1">
                        <div class="hex2 shadow">
                            <img src="/img/fitnessImage17.jpg" alt="" width="200" height="193" />
                        </div>
                    </div>
                </div>
                <p class="mt-4">Find a personal trainer and create a customized fitness plan that will "work out" for YOU!</p>
                <div class="text-center">
                <router-link to="/register" exact>
                    <button class="btn btn-lg btn-info mt-4">Register Now</button>
                </router-link>
                </div>
            </div>
        </div>
    </default-layout>
</template>

<script>
import DefaultLayout from '@/layouts/DefaultLayout';

export default {
    name: 'home',
    components: {
        DefaultLayout
    }

}
</script>

<style>

@import url('https://fonts.googleapis.com/css?family=Roboto:500|Rubik');

h1, h2, h3 {
    font-family: 'Roboto', sans-serif;
}

h4, h5, h6, p {
    font-family: 'Rubik', sans-serif;
}

.homeHeader {
    background-image: url('/img/fitnessImage15.jpg');
    background-size: cover;
     background-position: center;
    border-radius: 4px;
    height: 450px;
    
}

.subheading {
    background-color: rgba(91, 192, 222, 0.2);
    border-radius: 5px;
}

.blueTransparent {
    background-color: rgba(91, 192, 222, 0.2);
}

.orangeTransparent {
    background-color: rgba(218, 140, 24, 0.6);
}

.hexa, .hexa div {
  margin: 0 auto;
  transform-origin: 50% 50%;
  overflow: hidden;
  width: 200px;
  height: 200px;
}
.hexa {
  width: 235px;
  height: 140px;
}
.hexa div {
  width: 100%;
  height: 100%;
}
.hexa {
  transform: rotate(120deg);
}
.hex1 {
  transform: rotate(-60deg);
}
.hex2 {
  transform: rotate(-60deg);
}

  

</style>
