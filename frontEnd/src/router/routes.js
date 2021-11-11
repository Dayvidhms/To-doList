
const routes = [
  {
    path: '/',
    component: () => import('pages/Index.vue')
  },
  {
    path: '/quadro/:id',
    component: () => import('pages/Quadro.vue')
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/Error404.vue')
  }
]

export default routes
