import { createStore, createLogger } from 'vuex'

const debug = process.env.NODE_ENV !== 'production'
const store= createStore({
    state:{token:null},
    mutations:{
        updateToken(state,token){
            state.token=token;
        }
    },
    actions:{},
    modules: {
    },
    strict: debug,
    plugins: debug ? [createLogger()] : []
})
export default store;