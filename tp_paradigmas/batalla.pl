

%personaje(nombre,tipo,vida,energia)
%
%esta_debilitado(Nombre).

objetivos_recomendados(Nombre,Tipo):-personaje(Nombre,Tipo,Vida,_),esta_debilitado(Nombre),Vida>0.

% Reglas de decisión básicas en Prolog
% Regla para decidir atacar: ataca si hay enemigos debilitados

% Atacar si los puntos de vida son mayores a 100
decidir_accion(Puntos_vida, _, 'Atacar') :- 
    Puntos_vida > 150.

% Atacar si hay enemigos debilitados
decidir_accion(_, Enemigos_debilitados, 'Atacar') :- 
    Enemigos_debilitados > 0.

% Defender si la vida es baja y no hay enemigos debilitados
decidir_accion(Puntos_vida, Enemigos_debilitados, 'Defender') :- 
    Puntos_vida =< 150, Enemigos_debilitados = 0.

% Fallback: Si ninguna de las anteriores se cumple, elige Atacar por defecto
decidir_accion(_, _, 'Atacar').

%hechizo(Nombre,Personaje, Costo, Tipo).
hechizos_disponibles(NombrePersonaje, Energia, Tipo, HechizosDisponibles) :-
    findall(Nombre, (hechizo(Nombre, NombrePersonaje, Costo, Tipo), Costo =< Energia), HechizosDisponibles).
:- dynamic personaje/4.
:- dynamic hechizo/4.
:- dynamic esta_debilitado/1.
