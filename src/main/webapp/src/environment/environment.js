const {  NODE_ENV = '' } = process.env;

const environment = NODE_ENV.toLowerCase();
const apiBaseUrl = "http://localhost:8080";
//const apiBaseUrl="http://3.83.15.90/api/v1";

export { environment, apiBaseUrl };