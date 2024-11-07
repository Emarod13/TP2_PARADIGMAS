

%personaje(nombre,tipo,vida,energia)
%
%esta_debilitado(Nombre).

muertos(Personaje):-personaje(Personaje,_,Vida,_),Vida =<0.
objetivos_recomendados(Nombre,Tipo):-personaje(Nombre,Tipo,_,_),esta_debilitado(Nombre),\+muertos(Nombre).

% Reglas de decisión básicas en Prolog
% Regla para decidir atacar: ataca si hay enemigos debilitados

decidir_accion(Puntos_vida, _, 'Atacar'):- Puntos_vida >100.
decidir_accion(_, Enemigos_debilitados, 'Atacar') :-
    Enemigos_debilitados > 0.

% Regla para decidir defender: defiende si la vida es baja y no hay enemigos debilitados
decidir_accion(Puntos_vida, Enemigos_debilitados, 'Defender') :-
    Puntos_vida < 20, Enemigos_debilitados = 0.

% Regla para decidir consumir objeto: consume si su vida es menor al 50% y hay pocos enemigos debilitados
decidir_accion(Puntos_vida, Enemigos_debilitados, 'Consumir') :-
    Puntos_vida < 50, Enemigos_debilitados < 2.

%hechos. hechizo(Nombre,Personaje, Costo, Tipo).
hechizos_disponibles(NombrePersonaje, Energia, Tipo, HechizosDisponibles) :-
    findall(Nombre, (hechizo(Nombre, NombrePersonaje, Costo, Tipo), Costo =< Energia), HechizosDisponibles).
:- dynamic personaje/4.
:- dynamic hechizo/4.
