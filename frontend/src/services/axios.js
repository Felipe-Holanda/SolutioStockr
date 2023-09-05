import axios from 'axios';

const instance = axios.create({
    baseUrl: 'https://deploy-production-314a.up.railway.app'
})

export default instance;
