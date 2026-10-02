<p align="center"><img src="https://raw.githubusercontent.com/DrakesCraft-Labs/Drugfun/main/banner.svg" alt="Drugfun" width="100%"></p>

# Drugfun

> ### 🏰 ¡Únete a la Comunidad Oficial de DrakesCraft!
> 
> * 🎮 **IP del Servidor**: `play.drakescraft.net` *(Java 1.21.11 & Bedrock)*
> * 💬 **Discord Oficial**: [discord.gg/drakescraft](https://discord.gg/rv3vtXZTk7)
> * 🌐 **Web & Guía**: [drakescraft.net](https://drakescraft.net) — 🛒 **Tienda**: [tienda.drakescraft.net](https://tienda.drakescraft.net)
> 
> *¡Juega con este addon y más de 80 expansiones optimizadas en vivo en nuestra network de supervivencia técnica!*

---

Alquimia y brebajes para Slimefun, adaptado al ecosistema Slimefun de **DrakesCraft** (Paper/Purpur 1.21.11, Java 21).

## Qué añade

Brebajes con efectos, hechos con una versión propia de Brigadier para sus comandos.

## Qué cambiamos

Este repositorio **no es un fork**: es el código original integrado en el ecosistema de
DrakesCraft (Paper/Purpur 1.21.11, Java 21). Los cambios comunes a todos nuestros ports son:

**Los paquetes de Slimefun.** El core de DrakesCraft está repaquetado, así que un addon de fuera
no encuentra nada hasta que se remapean sus imports.

**La telemetría, fuera.** bStats abría una conexión a bstats.org cada pocos minutos con datos del
servidor. Se quitaron las llamadas, los imports y la dependencia — no se sustituyó por un stub
inerte, que dejaría el código en pie aparentando que hay telemetría.

**Los autoactualizadores, desarmados.** Este jar está recompilado contra nuestro Slimefun; si se
bajara el de upstream encima, dejaría de cargar. Las actualizaciones se despliegan por SFTP.

**El rastreador de fallos apunta aquí**, no al repositorio original: un fallo de esta versión
casi nunca es un fallo de allí.

---

## 📄 License & Upstream Attribution

This project is a sovereign fork maintained by [**JackStar6677-1**](https://github.com/JackStar6677-1) under [**DrakesCraft Labs**](https://github.com/DrakesCraft-Labs).

- **Original Project:** Created by the upstream authors and the open-source community.
- **DrakesCraft Optimizations:** Modernized for Paper/Purpur 1.21.11+, Java 21, high concurrency, asynchronous safety, and exploit/duplication prevention.
- **License:** Distributed under the original **GNU General Public License v3.0 (GPLv3)** (or original upstream license). See the [LICENSE](LICENSE) file for complete terms.
