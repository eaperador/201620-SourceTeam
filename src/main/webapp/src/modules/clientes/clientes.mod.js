(function (ng) {
    var mod = ng.module("clientesModule", ["ngMessages"]);
    mod.constant("clientesContext", "api/clientes");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/clientes/';
            $urlRouterProvider.otherwise("/clientesList");
     
            $stateProvider.state('clientesList', {
                url: '/clientes',
                views: {
                    'mainView': {
                        controller: 'clientesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'clientes.list.html'
                    }
                }
            }).state('clienteCreate', {
                url: '/clientes/create',
                views: {
                    'mainView': {
                        controller: 'clientesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'clientes.create.html'
                    }
                }

            }).state('clienteEdit', {
                url: '/clientes/:clienteNombre',
                param: {
                    clienteNombre: null
                },
                views: {
                    'mainView': {
                        controller: 'clientesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'clientes.create.html'
                    }
                }
            });
        }]);
})(window.angular);