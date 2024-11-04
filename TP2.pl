

%personaje(nombre,tipo,vida,energia)
%
%esta_debilitado(Nombre).

objetivos_recomendados(Nombre,Tipo):-personaje(Nombre,Tipo,_,_),esta_debilitado(Nombre).

% Reglas de decisión básicas en Prolog
% Regla para decidir atacar: ataca si hay enemigos debilitados
decidir_accion(puntos_vida, enemigos_debilitados, atacar) :-
    enemigos_debilitados > 0.

% Regla para decidir defender: defiende si la vida es baja y no hay enemigos debilitados
decidir_accion(puntos_vida, enemigos_debilitados, defender) :-
    puntos_vida < 20, enemigos_debilitados = 0.

% Regla para decidir consumir objeto: consume si su vida es menor al 50% y hay pocos enemigos debilitados
decidir_accion(puntos_vida, enemigos_debilitados, consumir) :-
    puntos_vida < 50, enemigos_debilitados < 2.

%hechos. hechizo(Nombre, Costo, Tipo).
hechizos_disponibles(Personaje,Energia, Tipo, HechizosDisponibles) :-
    findall(Nombre, (hechizo(Personaje,Nombre, Costo, TipoHechizo), Costo =< Energia, TipoHechizo = Tipo), HechizosDisponibles).
