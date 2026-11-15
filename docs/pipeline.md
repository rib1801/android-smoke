# Pipeline — decisões, versões verificadas e regras

Documento para agentes e para mim no futuro. Se algo aqui conflitar com o que você "acha que sabe" sobre Android, **este documento vence**: cada versão abaixo foi provada com build verde no CI.

## Versões fixadas (verificadas em 2026-11-15)

| peça | versão | por quê |
|---|---|---|
| AGP | 9.5.1 | Compose 1.12+ exige AGP 9 |
| Gradle | 9.9.1 (wrapper) | AGP 9.5 exige Gradle ≥ 9.6.0 |
| Kotlin | embutido no AGP (KGP 2.2.10) | AGP 9 tem built-in Kotlin |
| plugin Compose compiler | 2.4.21 | obrigatório mesmo com Kotlin embutido |
| compileSdk | 37 | Compose 1.12+ exige compileSdk 37 |
| targetSdk | 36 | Android 16 |
| minSdk | 26 | Android 8+ |
| Compose BOM | 2026.09.00 | |
| activity-compose | 1.13.0 | |
| JDK do runner | 21 (bytecode 17) | AGP 9 exige JDK 17+ |

## Armadilhas já pagas (não repita)

1. **`org.jetbrains.kotlin.android` é erro fatal no AGP 9**: "The 'org.jetbrains.kotlin.android' plugin is no longer required for Kotlin support since AGP 9.0". Não aplique na raiz nem no módulo.
2. **AGP 8.13.2 não compila Compose 1.12.1**: falha com "requires Android Gradle plugin 9.1.0 or higher" e "compile against version 37 or later of the Android APIs". Não tente voltar para AGP 8 "para estabilizar" — o ecossistema androidx já exige 9.
3. **O `kotlin { compilerOptions { } }` continua válido** mesmo com Kotlin embutido (só `android.kotlinOptions {}` está morto).
4. **Log do GitHub mascara valores de secrets**: o alias `smoke` aparece como `***` em logs; não é corrupção do APK.
5. `versionCode` nunca na mão. Vem de `GITHUB_RUN_NUMBER`, e por isso releases "pulam" números entre branches — é esperado.
6. **Repo privado quebra o Obtainium**: ele consulta a API anônima, recebe `404` e mostra "não foi possível localizar um lançamento adequado". Ou o repo é público, ou o usuário cola um PAT (escopo `repo`) em Settings → GitHub no Obtainium. Repo público também dá minutos de Actions ilimitados.
7. A visibilidade pública não expõe nada: keystore fica no `.gitignore` e secrets continuam ocultos.

## Regras inegociáveis

1. `applicationId` = `com.rib1801.smoke`. Mudar = app novo, dados perdidos.
2. Keystore só em GitHub Secrets (`KEYSTORE_BASE64`, `KEYSTORE_PASSWORD`, `KEY_ALIAS`, `KEY_PASSWORD`). Nunca commitar `.p12`/`.jks`.
3. Asset do APK sempre `app-release.apk` — o Obtainium faz match pelo nome.
4. `beta` publica com `--prerelease`; `main` publica release estável.
5. Mudou comportamento → atualiza `docs/spec.md` no mesmo commit.
6. Atualizar AGP/Gradle/Kotlin exige build verde antes de mergear. Sem exceção.

## Por que CI e não local

A VM de desenvolvimento tem 2GB de RAM; Gradle+AGP não sobem. Todo build roda em `ubuntu-latest` (Android SDK pré-instalado, licenças aceitas). Repo privado consome minutos do plano free: ~2-5 min por build.

## Como clonar este setup para um app novo

1. Criar repo novo e copiar: `settings.gradle.kts`, `build.gradle.kts`, `gradle/`, `gradlew`, `gradle.properties`, `.github/workflows/build.yml`, `.gitignore`.
2. Trocar `namespace`/`applicationId` e `rootProject.name`.
3. Reaproveitar os **mesmos secrets** (a mesma keystore serve para todos os apps seus).
4. Instalar no celular e apontar o Obtainium para o repo novo.
