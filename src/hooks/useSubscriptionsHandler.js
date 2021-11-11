import { ref } from 'vue'

const useSubscriptionsHandler = () => {
    const subscriptions = ref([])

    const subscribe = (id, notifyCallback) => {
        subscriptions.value.push({
            id,
            notifyCallback
        })

        console.log(subscriptions.value)
    }
    
    const unsubscribe = (id) => {
        let index = subscriptions.value.findIndex((elemento) => elemento.id == id)
        subscriptions.value.splice(index, 1)

        console.log(subscriptions.value)
    }

    const notifyAll = () => {
        subscriptions.value.forEach(subscription => {
            subscription.notifyCallback();
        })
    }

    return {
        subscribe,
        unsubscribe,
        notifyAll
    }
}

export default useSubscriptionsHandler