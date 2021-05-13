const {  NODE_ENV = '' } = process.env;

const environment = NODE_ENV.toLowerCase();
const apiBaseUrl = "http://localhost:8080";

export { environment, apiBaseUrl };