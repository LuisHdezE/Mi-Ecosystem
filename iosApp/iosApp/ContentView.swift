import SwiftUI
import Shared

struct ContentView: View {
    private let info = EcosystemInfo()

    var body: some View {
        let identity = BootstrapIdentity.shared.product
        let theme = identity.theme
        let colors = theme.colors

        VStack(spacing: 24) {
            VStack(spacing: 4) {
                Text(identity.displayName)
                    .font(.largeTitle.weight(.bold))
                    .foregroundStyle(colors.primary.toSwiftUIColor())
                Text("Technical Bootstrap")
                    .font(.title3)
                    .foregroundStyle(colors.secondary.toSwiftUIColor())
            }

            VStack(spacing: 8) {
                Text("Theme: \(theme.id)")
                    .font(.body)
                Text("Theme version: \(theme.version)")
                    .font(.body)
                Text("Shared identity: OK")
                    .font(.body)

                Spacer().frame(height: 8)

                Text("Platform: iOS")
                    .font(.body)
            }
            .foregroundStyle(colors.onBackground.toSwiftUIColor())
        }
        .multilineTextAlignment(.center)
        .padding()
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(colors.background.toSwiftUIColor())
    }
}

extension ColorToken {
    func toSwiftUIColor() -> Color {
        let argbValue = self.argb
        let a = Double((argbValue >> 24) & 0xFF) / 255.0
        let r = Double((argbValue >> 16) & 0xFF) / 255.0
        let g = Double((argbValue >> 8) & 0xFF) / 255.0
        let b = Double(argbValue & 0xFF) / 255.0

        return Color(red: r, green: g, blue: b, opacity: a)
    }
}
