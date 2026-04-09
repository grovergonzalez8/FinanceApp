# AGENTS.md

## Engineering Rules
- Use real Clean Architecture
- Keep Presentation, Domain, and Data clearly separated
- Do not place business logic in UI
- Domain must not depend on Android or framework-specific classes
- Data must not depend on Presentation
- Prefer explicit mappers over hidden transformations
- Keep files small and focused
- Use clear and consistent naming
- Avoid unnecessary abstractions
- Avoid duplicated logic
- Prefer maintainability over cleverness
- Add tests for business logic and important ViewModel behavior

## UI Rules
- Use Jetpack Compose
- Use Material 3
- Base visual identity on dark blue
- Keep the interface modern, minimal, and premium
- Reuse components where possible
- Handle loading, success, empty, and error states clearly

## Stack Rules
- Kotlin
- Jetpack Compose
- Hilt
- Room
- DataStore
- Coroutines
- Flow / StateFlow
- Navigation Compose

## Delivery Rules
- Work only on the requested phase
- Do not jump ahead to future phases
- Do not create unrelated files
- Keep every phase aligned with the project brief
- Explain architectural conflicts before applying changes
- Prefer code that can realistically evolve into production

## Output Rules
- Return work file by file
- Include full file path
- Include complete code
- No pseudocode
- Keep explanations short and technical