<template>
    <default-layout>
        <div class="jumbotron h-100 homeHeader shadow">
            <div class="container">
                <div class="site-heading text-light">
                    <h1 class="display-4">Welcome to Trainer Portal</h1>
                    <h3>A Client - Trainer Matcher</h3>
                     <hr class="my-4">
                    <h2 class="lead">Find a personal trainer and create a customized fitness plan that will "work out" for YOU!</h2>
                </div>
                </div>
        </div>
        <div class="row">
            <div class="col subheading p-2 shadow">
                <h4 class="orangeText text-center">Login/Sign Up</h4>
                <hr class="my-3">
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ut justo tempus, ornare magna nec, malesuada ipsum. Sed nec scelerisque mauris. Nam urna sapien, consectetur vitae nisl quis, tristique luctus libero. Aenean eu aliquam ipsum, et semper sapien. Duis eget erat id velit tincidunt placerat. Sed pellentesque bibendum diam quis efficitur. Pellentesque eu leo tempus, mollis augue vitae, vestibulum enim.</p>
            </div>
            <div class="col subheading p-2 ml-2 shadow">
                <h4 class="orangeText text-center">Benefits</h4>
                <hr class="my-3">
                <p>Proin porttitor ante eget accumsan efficitur. Phasellus sollicitudin, tellus sit amet mattis vehicula, ligula elit vestibulum lectus, sit amet ullamcorper turpis eros ut purus. Nam mollis neque felis. Pellentesque tincidunt justo vel leo eleifend, at sollicitudin nibh imperdiet. Vestibulum ut elementum ante. Etiam lacinia, odio sit amet consequat varius, velit velit pharetra urna, et scelerisque urna tellus rhoncus ipsum. Maecenas lacinia mauris faucibus massa posuere vestibulum.</p>
            </div>
            <div class="col subheading p-2 ml-2 shadow">
                <h4 class="orangeText text-center">About</h4>
                <hr class="my-3">
                <p>Sed in sem et sem imperdiet porta. Duis faucibus nunc leo, quis tempus massa malesuada et. Donec suscipit mauris non ex sodales bibendum. Donec id tempor turpis. Phasellus et lectus vitae purus ultricies ullamcorper in in diam. Mauris aliquet eros urna, a sodales lacus posuere eu. Curabitur aliquam at est sit amet tincidunt. Morbi non lectus at felis molestie laoreet vel nec ex. Nulla leo tortor, luctus et tellus auctor, malesuada commodo mi.</p>
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

.homeHeader {
    background-image: url("../assets/FitnessImages/fitnessImage1.jpg");
    background-size: cover;
    background-position: center;
    border-radius: 4px;
    
}

.subheading {
    background-color: #e9f5f8;
    border-radius: 5px;
}

</style>
