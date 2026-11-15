# Pipeline — decisões e regras

Documento para agentes e para mim no futuro. Se algo aqui conflitar com o que você acha que sabe sobre Android, **este documento vence**: foi verificado com build real.

## Versões fixadas (verificadas)

- Kotlin 2.4.21 + `org.jetbrains.kotlin.plugin.compose` 2.4.21
- AGP 8.13.2
- Gradle 8.14.7 (wrapper)
- compileSdk 36 · targetSdk 36 · minSdk 26 · JDK 17 (bytecode) / JDK 21 (runner)

Não atualize AGP/Gradle/Kotlin "porque saiu versão nova". Atualize, rode o build, e só mantenha se ficar verde.

## Regras inegociáveis

1. `versionCode` = `GITHUB_RUN_NUMBER` (em `app/build.gradle.kts`). Número na mão = update rejeitado pelo Android.
2. `applicationId` = `com.rib1801.smoke`. Mudar = app novo, dados perdidos.
3. Keystore só em GitHub Secrets. Nunca commitar `.p12`/`.jks` (já está no `.gitignore`).
4. Asset do APK sempre `app-release.apk` — o Obtainium faz match por nome.
5. `beta` publica com `--prerelease`, `main` publica como release estável.
6. Mudou comportamento → atualiza `docs/spec.md` no mesmo commit.

## Por que CI e não local

A VM de desenvolvimento tem 2GB de RAM; Gradle+AGP não sobem. Todo build acontece em `ubuntu-latest` (Android SDK pré-instalado, licenças aceitas). Custo: repo privado consome minutos do plano free (~4-6 min por build, 2000 min/mês).

## Como clonar este setup para um app novo

1. Criar repo novo, copiar `settings.gradle.kts`, `build.gradle.kts`, `gradle/`, `gradlew`, `gradle.properties`, `.github/workflows/build.yml`, `.gitignore`.
2. Trocar `namespace`/`applicationId` e `rootProject.name`.
3. Reaproveitar os **mesmos secrets** (mesma keystore serve para todos os apps seus) — ou gerar keystore nova se quiser isolar.
4. Instalar o app novo no celular e apontar o Obtainium para o repo novo.
